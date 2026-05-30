package com.sip.tp.service;

import com.sip.tp.entity.Candidate;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.ProjectRepository;
import com.sip.tp.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CandidateSkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final WorkExperienceRepository experienceRepository;

    /**
     * Algorithm 2.8: Profile Completion Calculation
     */
    public int calculateProfileCompletion(Candidate candidate) {
        int completion = 0;

        if (candidate.getProfilePhoto() != null && !candidate.getProfilePhoto().isBlank()) completion += 10;
        if (candidate.getHeadline() != null && !candidate.getHeadline().isBlank()) completion += 10;
        if (candidate.getIdentityVerified()) completion += 10;

        long skillCount = skillRepository.countByCandidateId(candidate.getId());
        if (skillCount > 0) completion += 20;

        long validatedSkillCount = skillRepository.countValidatedByCandidateId(candidate.getId());
        if (validatedSkillCount > 0) completion += 20;

        if (projectRepository.existsByCandidateId(candidate.getId())) completion += 10;
        if (experienceRepository.existsByCandidateId(candidate.getId())) completion += 20;

        return Math.min(completion, 100);
    }

    /**
     * Algorithm 2.7: Identity Verification (RENAPER Mock)
     */
    public boolean verifyIdentity(Candidate candidate, String dni, String tramiteNumber) {
        // In a real scenario, this calls the external RENAPER API
        boolean isValid = mockRenaperApiCall(dni, tramiteNumber);

        if (isValid) {
            candidate.setIdentityVerified(true);
            // Profile completion score should be updated subsequently
        }
        return isValid;
    }

    private boolean mockRenaperApiCall(String dni, String tramite) {
        return dni != null && tramite != null && tramite.length() > 5;
    }
}