package com.sip.tp.service;

import com.sip.tp.dto.request.AddSkillRequest;
import com.sip.tp.dto.skill.CandidateSkillResponse;
import com.sip.tp.dto.skill.SkillResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Skill;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.SkillRepository;
import com.sip.tp.types.definition.ExperienceRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final CandidateRepository candidateRepository;
    private final ProfileService profileAlgorithms;

    @Transactional(readOnly = true)
    public List<SkillResponse> searchCatalog(String search) {
        return skillRepository.findByNameContainingIgnoreCase(search).stream()
                .map(s -> new SkillResponse(s.getId(), s.getName(), s.getType().code()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SkillResponse> getSuggestedSkillsForCandidate(UUID candidateId) {
        // In a real app, this would use an ML model or a mapping table based on `candidate.getCurrentRole()`.
        // Returning top 5 overall skills as a fallback implementation.
        return skillRepository.findAll().stream().limit(5)
                .map(s -> new SkillResponse(s.getId(), s.getName(), s.getType().code()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CandidateSkillResponse> getCandidateSkills(UUID candidateId) {
        return candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .map(cs -> new CandidateSkillResponse(
                        cs.getId(),
                        cs.getSkill().getName(),
                        cs.getExperienceRange().code(),
                        cs.getConsolidatedLevel() != null ? cs.getConsolidatedLevel().code() : null,
                        cs.getConsolidatedScore() != null ? cs.getConsolidatedScore().doubleValue() : null
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addSkillToCandidate(UUID candidateId, AddSkillRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Skill skill = skillRepository.findById(request.skillId()).orElseThrow();

        ExperienceRange range = switch (request.experienceRange()) {
            case "<1 year" -> new ExperienceRange.LessThan1Year();
            case "1-3 years" -> new ExperienceRange.Years1To3();
            case "4-6 years" -> new ExperienceRange.Years4To6();
            case "7-10 years" -> new ExperienceRange.Years7To10();
            case "10+ years" -> new ExperienceRange.Years10Plus();
            default -> throw new IllegalArgumentException("Invalid experience range");
        };

        CandidateSkill candidateSkill = CandidateSkill.builder()
                .candidate(candidate)
                .skill(skill)
                .experienceRange(range)
                .build();

        candidateSkillRepository.save(candidateSkill);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
    }

    @Transactional
    public void removeSkillFromCandidate(UUID candidateId, UUID candidateSkillId) {
        CandidateSkill skill = candidateSkillRepository.findById(candidateSkillId).orElseThrow();
        if (!skill.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");

        candidateSkillRepository.delete(skill);
        Candidate candidate = skill.getCandidate();
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
    }
}