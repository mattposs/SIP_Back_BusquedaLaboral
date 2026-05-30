package com.sip.tp.service;

import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Project;
import com.sip.tp.entity.WorkExperience;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.ProjectRepository;
import com.sip.tp.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidatorSuggestionService {

    private final CandidateSkillRepository candidateSkillRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final ProjectRepository projectRepository;

    /**
     * Algorithm 2.9: Validator Suggestion Algorithm (Fully Implemented)
     */
    @Transactional(readOnly = true)
    public List<SuggestedValidator> suggestValidatorsForSkill(UUID requesterId, UUID skillId) {

        // 1. Fetch the requester's history once to avoid N+1 queries in the loop
        List<WorkExperience> requesterExp = workExperienceRepository.findAllByCandidateId(requesterId);
        List<Project> requesterProj = projectRepository.findAllByCandidateId(requesterId);

        // 2. Find all users who have this skill validated (excluding the requester)
        List<CandidateSkill> peersWithSkill = candidateSkillRepository.findAll().stream()
                .filter(cs -> cs.getSkill().getId().equals(skillId))
                .filter(cs -> !cs.getCandidate().getId().equals(requesterId))
                .filter(cs -> cs.getConsolidatedLevel() != null) // Must be validated
                .toList();

        // 3. Calculate "Confidence Score" with Proximity Multiplier and map to DTO
        return peersWithSkill.stream()
                .map(cs -> {
                    Candidate peer = cs.getCandidate();

                    // Base confidence from the peer's own skill score (0.0 - 10.0)
                    double baseConfidence = cs.getConsolidatedScore() != null ?
                            cs.getConsolidatedScore().doubleValue() : 0.0;

                    // Calculate proximity multiplier
                    double proximityMultiplier = calculateProximityMultiplier(requesterExp, requesterProj, peer.getId());

                    // Final score
                    double finalConfidenceScore = baseConfidence * proximityMultiplier;

                    return new SuggestedValidator(
                            peer.getId(),
                            peer.getFullName(),
                            peer.getCurrentRoleTitle(),
                            finalConfidenceScore
                    );
                })
                .sorted((a, b) -> Double.compare(b.confidenceScore(), a.confidenceScore())) // Sort desc
                .limit(5) // Suggest top 5 matches
                .collect(Collectors.toList());
    }

    /**
     * Evaluates shared experiences to create a score multiplier.
     * Base is 1.0. Shared company adds 0.5. Shared project adds 0.3.
     */
    private double calculateProximityMultiplier(List<WorkExperience> requesterExp, List<Project> requesterProj, UUID peerId) {
        double multiplier = 1.0;

        List<WorkExperience> peerExp = workExperienceRepository.findAllByCandidateId(peerId);
        List<Project> peerProj = projectRepository.findAllByCandidateId(peerId);

        // Check for shared company + overlapping timeframe
        boolean hasSharedCompany = requesterExp.stream().anyMatch(reqWork ->
                peerExp.stream().anyMatch(peerWork ->
                        isSameString(reqWork.getCompany(), peerWork.getCompany()) &&
                                hasDateOverlap(reqWork, peerWork)
                )
        );

        if (hasSharedCompany) {
            multiplier += 0.5;
        }

        // Check for shared projects (basic title matching)
        boolean hasSharedProject = requesterProj.stream().anyMatch(reqP ->
                peerProj.stream().anyMatch(peerP ->
                        isSameString(reqP.getTitle(), peerP.getTitle())
                )
        );

        if (hasSharedProject) {
            multiplier += 0.3;
        }

        return multiplier;
    }

    /**
     * Determines if two work experiences overlap in time.
     * Null end dates are treated as "Current" (i.e., today).
     */
    private boolean hasDateOverlap(WorkExperience w1, WorkExperience w2) {
        LocalDate start1 = w1.getStartDate();
        LocalDate end1 = w1.getEndDate() != null ? w1.getEndDate() : LocalDate.now();

        LocalDate start2 = w2.getStartDate();
        LocalDate end2 = w2.getEndDate() != null ? w2.getEndDate() : LocalDate.now();

        // Standard date overlap formula: StartA <= EndB AND StartB <= EndA
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    /**
     * Normalizes and compares strings for fuzzy matching.
     */
    private boolean isSameString(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        // Strip whitespace and compare ignoring case
        return s1.trim().equalsIgnoreCase(s2.trim());
    }

    public record SuggestedValidator(UUID candidateId, String fullName, String seniority, double confidenceScore) {
    }
}