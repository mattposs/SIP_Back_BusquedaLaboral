package com.sip.tp.service.domain.candidate;

import com.sip.tp.entity.Candidate;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.ProjectRepository;
import com.sip.tp.repository.WorkExperienceRepository;
import com.sip.tp.service.algorithm.ProfileCompletionCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CandidateSkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final WorkExperienceRepository experienceRepository;
    private final ProfileCompletionCalculator profileCompletionCalculator;

    public int calculateProfileCompletion(Candidate candidate) {
        ProfileCompletionCalculator.ProfileSignals signals = new ProfileCompletionCalculator.ProfileSignals(
                candidate.getProfilePhoto() != null && !candidate.getProfilePhoto().isBlank(),
                candidate.getHeadline() != null && !candidate.getHeadline().isBlank(),
                candidate.getIdentityVerified(),
                skillRepository.countByCandidateId(candidate.getId()),
                skillRepository.countValidatedByCandidateId(candidate.getId()),
                projectRepository.existsByCandidateId(candidate.getId()),
                experienceRepository.existsByCandidateId(candidate.getId())
        );

        return profileCompletionCalculator.calculate(signals);
    }

    public boolean verifyIdentity(Candidate candidate, String dni, String tramiteNumber) {
        boolean isValid = mockRenaperApiCall(dni, tramiteNumber);
        if (isValid) {
            candidate.setIdentityVerified(true);
        }
        return isValid;
    }

    private boolean mockRenaperApiCall(String dni, String tramite) {
        return dni != null && tramite != null && tramite.length() > 5;
    }
}
