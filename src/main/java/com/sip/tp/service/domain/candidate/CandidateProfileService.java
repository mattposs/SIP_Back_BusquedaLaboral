package com.sip.tp.service.domain.candidate;

import com.sip.tp.dto.request.ProfileUpdateRequest;
import com.sip.tp.dto.request.ProjectRequest;
import com.sip.tp.dto.request.WorkExperienceRequest;
import com.sip.tp.dto.response.CandidateProfileResponse;
import com.sip.tp.dto.response.CompletionResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Project;
import com.sip.tp.entity.WorkExperience;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.ProjectRepository;
import com.sip.tp.repository.WorkExperienceRepository;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {

    private final CandidateRepository candidateRepository;
    private final WorkExperienceRepository experienceRepository;
    private final ProjectRepository projectRepository;
    private final ProfileService profileAlgorithms;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public CandidateProfileResponse getCandidateProfile(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
        return responseConverter.toCandidateProfileResponse(candidate);
    }

    @Transactional
    public void updateProfile(UUID candidateId, ProfileUpdateRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        requestConverter.applyProfileUpdate(candidate, request);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        candidateRepository.save(candidate);
    }

    @Transactional
    public String uploadProfilePhoto(UUID candidateId, MultipartFile file) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        String fileUrl = "https://storage.skillpassport.com/candidates/" + candidateId + "/" + file.getOriginalFilename();
        candidate.setProfilePhoto(fileUrl);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        candidateRepository.save(candidate);
        return fileUrl;
    }

    @Transactional(readOnly = true)
    public CompletionResponse getProfileCompletion(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        return responseConverter.toCompletionResponse(profileAlgorithms.calculateProfileCompletion(candidate));
    }

    @Transactional
    public boolean verifyIdentity(UUID candidateId, String dni, String tramiteNumber) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        boolean verified = profileAlgorithms.verifyIdentity(candidate, dni, tramiteNumber);
        if (verified) {
            candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
            candidateRepository.save(candidate);
        }
        return verified;
    }

    @Transactional
    public UUID addWorkExperience(UUID candidateId, WorkExperienceRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        WorkExperience experience = requestConverter.toWorkExperience(candidate, request);
        WorkExperience saved = experienceRepository.save(experience);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        return saved.getId();
    }

    @Transactional
    public void removeWorkExperience(UUID candidateId, UUID experienceId) {
        WorkExperience experience = experienceRepository.findById(experienceId).orElseThrow();
        if (!experience.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");
        experienceRepository.delete(experience);
    }

    @Transactional
    public void updateWorkExperience(UUID candidateId, UUID experienceId, WorkExperienceRequest request) {
        WorkExperience experience = experienceRepository.findById(experienceId).orElseThrow();
        if (!experience.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");
        requestConverter.applyWorkExperienceUpdate(experience, request);
        experienceRepository.save(experience);
    }

    @Transactional
    public UUID addProject(UUID candidateId, ProjectRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Project project = requestConverter.toProject(candidate, request);
        Project saved = projectRepository.save(project);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        return saved.getId();
    }

    @Transactional
    public void removeProject(UUID candidateId, UUID projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        if (!project.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");
        projectRepository.delete(project);
    }

    @Transactional
    public void updateProject(UUID candidateId, UUID projectId, ProjectRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        if (!project.getCandidate().getId().equals(candidateId)) throw new SecurityException("Unauthorized");
        requestConverter.applyProjectUpdate(project, request);
        projectRepository.save(project);
    }
}
