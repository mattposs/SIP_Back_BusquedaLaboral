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
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
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
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public List<SkillResponse> searchCatalog(String search) {
        return skillRepository.findByNameContainingIgnoreCase(search).stream()
                .map(responseConverter::toSkillResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SkillResponse> getSuggestedSkillsForCandidate(UUID candidateId) {
        return skillRepository.findAll().stream().limit(5)
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
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Skill skill = skillRepository.findById(request.skillId()).orElseThrow();

        CandidateSkill candidateSkill = CandidateSkill.builder()
                .candidate(candidate)
                .skill(skill)
                .experienceRange(requestConverter.toExperienceRange(request.experienceRange()))
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
