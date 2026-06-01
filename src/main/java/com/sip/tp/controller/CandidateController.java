package com.sip.tp.controller;

import com.sip.tp.dto.request.ProfileUpdateRequest;
import com.sip.tp.dto.request.VerifyIdentityRequest;
import com.sip.tp.dto.request.WorkExperienceRequest;
import com.sip.tp.dto.request.ProjectRequest;
import com.sip.tp.dto.response.CompletionResponse;
import com.sip.tp.service.CandidateProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/candidates/me")
@RequiredArgsConstructor
@Tag(name = "Candidate Profile", description = "Endpoints for managing the logged-in candidate's profile")
public class CandidateController {

    private final CandidateProfileService candidateService;

    @GetMapping
    @Operation(summary = "Get own profile")
    public ResponseEntity<Object> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(candidateService.getCandidateProfile(candidateId));
    }

    @PutMapping
    @Operation(summary = "Update profile fields")
    public ResponseEntity<Void> updateProfile(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody ProfileUpdateRequest request) {
        candidateService.updateProfile(candidateId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/photo")
    @Operation(summary = "Upload profile photo")
    public ResponseEntity<String> uploadPhoto(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(candidateService.uploadProfilePhoto(candidateId, file));
    }

    @GetMapping("/completion")
    @Operation(summary = "Get profile completion percentage")
    public ResponseEntity<CompletionResponse> getCompletion(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        int completion = candidateService.getProfileCompletion(candidateId);
        return ResponseEntity.ok(new CompletionResponse(completion));
    }

    @PostMapping("/verify-identity")
    @Operation(summary = "Initiate RENAPER verification")
    public ResponseEntity<Boolean> verifyIdentity(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody VerifyIdentityRequest request) {
        return ResponseEntity.ok(candidateService.verifyIdentity(candidateId, request.dni(), request.tramiteNumber()));
    }

    @PostMapping("/experience")
    @Operation(summary = "Add work experience")
    public ResponseEntity<UUID> addExperience(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody WorkExperienceRequest request) {
        return ResponseEntity.ok(candidateService.addWorkExperience(candidateId, request));
    }

    @PutMapping("/experience/{id}")
    @Operation(summary = "Update work experience")
    public ResponseEntity<Void> updateExperience(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id,
            @RequestBody WorkExperienceRequest request) {
        candidateService.updateWorkExperience(candidateId, id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/experience/{id}")
    @Operation(summary = "Remove work experience")
    public ResponseEntity<Void> removeExperience(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id) {
        candidateService.removeWorkExperience(candidateId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects")
    @Operation(summary = "Add project")
    public ResponseEntity<UUID> addProject(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(candidateService.addProject(candidateId, request));
    }

    @PutMapping("/projects/{id}")
    @Operation(summary = "Update project")
    public ResponseEntity<Void> updateProject(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id,
            @RequestBody ProjectRequest request) {
        candidateService.updateProject(candidateId, id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/projects/{id}")
    @Operation(summary = "Remove project")
    public ResponseEntity<Void> removeProject(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id) {
        candidateService.removeProject(candidateId, id);
        return ResponseEntity.noContent().build();
    }
}