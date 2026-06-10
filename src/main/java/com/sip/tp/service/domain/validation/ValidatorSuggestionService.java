package com.sip.tp.service.domain.validation;

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

    @Transactional(readOnly = true)
    public List<SuggestedValidator> suggestValidatorsForSkill(UUID requesterId, UUID skillId) {
        List<WorkExperience> requesterExp = workExperienceRepository.findAllByCandidateId(requesterId);
        List<Project> requesterProj = projectRepository.findAllByCandidateId(requesterId);

        List<CandidateSkill> peersWithSkill = candidateSkillRepository.findAll().stream()
                .filter(cs -> cs.getSkill().getId().equals(skillId))
                .filter(cs -> !cs.getCandidate().getId().equals(requesterId))
//                .filter(cs -> cs.getConsolidatedLevel() != null) // TODO: Verificar acá qué debería hacer
                .toList();

        return peersWithSkill.stream()
                .map(cs -> {
                    Candidate peer = cs.getCandidate();
                    double baseConfidence = cs.getConsolidatedScore() != null ? cs.getConsolidatedScore().doubleValue() : 0.0;
                    double proximityMultiplier = calculateProximityMultiplier(requesterExp, requesterProj, peer.getId());
                    double finalConfidenceScore = baseConfidence * proximityMultiplier;

                    return new SuggestedValidator(peer.getId(), peer.getFullName(), peer.getCurrentRoleTitle(), finalConfidenceScore);
                })
                .sorted((a, b) -> Double.compare(b.confidenceScore(), a.confidenceScore()))
                .limit(5)
                .collect(Collectors.toList());
    }

    private double calculateProximityMultiplier(List<WorkExperience> requesterExp, List<Project> requesterProj, UUID peerId) {
        double multiplier = 1.0;

        List<WorkExperience> peerExp = workExperienceRepository.findAllByCandidateId(peerId);
        List<Project> peerProj = projectRepository.findAllByCandidateId(peerId);

        boolean hasSharedCompany = requesterExp.stream().anyMatch(reqWork ->
                peerExp.stream().anyMatch(peerWork ->
                        isSameString(reqWork.getCompany(), peerWork.getCompany()) && hasDateOverlap(reqWork, peerWork)
                )
        );

        if (hasSharedCompany) {
            multiplier += 0.5;
        }

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

    private boolean hasDateOverlap(WorkExperience w1, WorkExperience w2) {
        LocalDate start1 = w1.getStartDate();
        LocalDate end1 = w1.getEndDate() != null ? w1.getEndDate() : LocalDate.now();

        LocalDate start2 = w2.getStartDate();
        LocalDate end2 = w2.getEndDate() != null ? w2.getEndDate() : LocalDate.now();

        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    private boolean isSameString(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        return s1.trim().equalsIgnoreCase(s2.trim());
    }

    public record SuggestedValidator(UUID candidateId, String fullName, String seniority, double confidenceScore) {
    }
}
