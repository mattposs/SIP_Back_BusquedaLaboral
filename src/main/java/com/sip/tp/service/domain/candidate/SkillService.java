package com.sip.tp.service.domain.candidate;

import com.sip.tp.dto.request.AddSkillRequest;
import com.sip.tp.dto.skill.CandidateSkillResponse;
import com.sip.tp.dto.skill.SkillResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Skill;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.SkillRepository;
import com.sip.tp.service.domain.matching.MatchGenerationService;
import com.sip.tp.types.definition.SkillType;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final CandidateRepository candidateRepository;
    private final MatchGenerationService matchGenerationService;
    private final ProfileService profileAlgorithms;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public List<SkillResponse> searchCatalog(String search) {
        if (search == null || search.isBlank()) {
            return skillRepository.findAll().stream()
                    .limit(20)
                    .map(responseConverter::toSkillResponse)
                    .collect(Collectors.toList());
        }
        return skillRepository.findByNameContainingIgnoreCase(search).stream()
                .map(responseConverter::toSkillResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SkillResponse> getSuggestedSkills(UUID userId) {
        return candidateRepository.findById(userId)
                .map(candidate -> getSuggestedSkillsForCandidate(userId))
                .orElseGet(this::getPopularSkills);
    }

    @Transactional(readOnly = true)
    public List<SkillResponse> getPopularSkills() {
        return skillRepository.findAll().stream()
                .filter(skill -> skill.getType() instanceof SkillType.Tech)
                .limit(10)
                .map(responseConverter::toSkillResponse)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<SkillResponse> getSuggestedSkillsForCandidate(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Set<UUID> ownedSkillIds = candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .map(cs -> cs.getSkill().getId())
                .collect(Collectors.toSet());

        Set<Skill> suggestions = new LinkedHashSet<>();
        String role = candidate.getCurrentRoleTitle();
        if (role != null && !role.isBlank()) {
            skillRepository.findByNameContainingIgnoreCase(role).stream()
                    .filter(skill -> !ownedSkillIds.contains(skill.getId()))
                    .forEach(suggestions::add);
            for (String token : role.split("\\s+")) {
                if (token.length() < 3) {
                    continue;
                }
                skillRepository.findByNameContainingIgnoreCase(token).stream()
                        .filter(skill -> !ownedSkillIds.contains(skill.getId()))
                        .forEach(suggestions::add);
            }
        }

        if (suggestions.size() < 10) {
            skillRepository.findAll().stream()
                    .filter(skill -> skill.getType() instanceof SkillType.Tech)
                    .filter(skill -> !ownedSkillIds.contains(skill.getId()))
                    .forEach(suggestions::add);
        }

        return suggestions.stream()
                .limit(10)
                .map(responseConverter::toSkillResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CandidateSkillResponse> getCandidateSkills(UUID candidateId) {
        return candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .map(responseConverter::toCandidateSkillResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addSkillToCandidate(UUID candidateId, AddSkillRequest request) {
        if (candidateSkillRepository.existsByCandidateIdAndSkillId(candidateId, request.skillId())) {
            return;
        }

        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Skill skill = skillRepository.findById(request.skillId()).orElseThrow();

        CandidateSkill candidateSkill = CandidateSkill.builder()
                .candidate(candidate)
                .skill(skill)
                .experienceRange(requestConverter.toExperienceRange(request.experienceRange()))
                .build();

        candidateSkillRepository.save(candidateSkill);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        matchGenerationService.recomputeMatchesForCandidate(candidateId);
    }

    @Transactional
    public void removeSkillFromCandidate(UUID candidateId, UUID candidateSkillId) {
        CandidateSkill skill = candidateSkillRepository.findById(candidateSkillId).orElseThrow();
        if (!skill.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");

        candidateSkillRepository.delete(skill);
        Candidate candidate = skill.getCandidate();
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        matchGenerationService.recomputeMatchesForCandidate(candidateId);
    }
}
