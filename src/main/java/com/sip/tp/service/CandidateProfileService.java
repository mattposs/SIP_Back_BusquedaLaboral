package com.sip.tp.service;

import com.sip.tp.dto.request.ProfileUpdateRequest;
import com.sip.tp.dto.request.ProjectRequest;
import com.sip.tp.dto.request.WorkExperienceRequest;
import com.sip.tp.dto.response.CandidateProfileResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Project;
import com.sip.tp.entity.WorkExperience;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.ProjectRepository;
import com.sip.tp.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {

    private final CandidateRepository candidateRepository;
    private final WorkExperienceRepository experienceRepository;
    private final ProjectRepository projectRepository;
    private final ProfileService profileAlgorithms;

    @Transactional(readOnly = true)
    public CandidateProfileResponse getCandidateProfile(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
        return new CandidateProfileResponse(
                candidate.getId(), candidate.getFullName(), candidate.getLocation(),
                candidate.getCurrentRoleTitle(), candidate.getHeadline(), candidate.getProfilePhoto(),
                candidate.getIdentityVerified(), candidate.getProfileCompletion()
        );
    }

    @Transactional
    public void updateProfile(UUID candidateId, ProfileUpdateRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        candidate.setLocation(request.location());
        candidate.setCurrentRoleTitle(request.currentRole());
        candidate.setHeadline(request.headline());
        candidate.setPhone(request.phone());
        candidate.setLinkedIn(request.linkedIn());

        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        candidateRepository.save(candidate);
    }

    @Transactional
    public String uploadProfilePhoto(UUID candidateId, MultipartFile file) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        // Mocking cloud storage upload (e.g., AWS S3)
        String fileUrl = "https://storage.skillpassport.com/candidates/" + candidateId + "/" + file.getOriginalFilename();
        candidate.setProfilePhoto(fileUrl);
        candidate.setProfileCompletion(profileAlgorithms.calculateProfileCompletion(candidate));
        candidateRepository.save(candidate);
        return fileUrl;
    }

    @Transactional(readOnly = true)
    public int getProfileCompletion(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        return profileAlgorithms.calculateProfileCompletion(candidate);
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
        WorkExperience experience = WorkExperience.builder()
                .candidate(candidate)
                .company(request.company())
                .position(request.position())
                .startDate(LocalDate.parse(request.startDate()))
                .endDate(request.endDate() != null ? LocalDate.parse(request.endDate()) : null)
                .isCurrent(request.isCurrent())
                .description(request.description())
                .build();

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
        experience.setCompany(request.company());
        experience.setPosition(request.position());
        experience.setStartDate(LocalDate.parse(request.startDate()));
        experience.setEndDate(request.endDate() != null ? LocalDate.parse(request.endDate()) : null);
        experience.setIsCurrent(request.isCurrent());
        experience.setDescription(request.description());
        experienceRepository.save(experience);
    }

    @Transactional
    public UUID addProject(UUID candidateId, ProjectRequest request) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Project project = Project.builder()
                .candidate(candidate)
                .title(request.title())
                .description(request.description())
                .link(request.link())
                .build();

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
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setLink(request.link());
        projectRepository.save(project);
    }
}