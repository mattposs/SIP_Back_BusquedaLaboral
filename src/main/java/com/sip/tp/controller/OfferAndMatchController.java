package com.sip.tp.controller;

import com.sip.tp.service.CompanyService;
import com.sip.tp.service.JobOfferService;
import com.sip.tp.service.MatchFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OfferAndMatchController {

    private final CompanyService companyService;
    private final JobOfferService jobOfferService;
    private final MatchFlowService matchService;

    // --- Company ---
    @Tag(name = "Company", description = "Company profile management")
    @PostMapping("/companies")
    public ResponseEntity<UUID> createCompany(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @RequestBody CompanyRequest request) {
        return ResponseEntity.ok(companyService.createCompany(recruiterId, request));
    }

    @Tag(name = "Company")
    @PutMapping("/companies/{id}")
    public ResponseEntity<Void> updateCompany(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID id,
            @RequestBody CompanyRequest request) {
        companyService.updateCompany(recruiterId, id, request);
        return ResponseEntity.ok().build();
    }

    // --- Job Offers (Recruiter View) ---
    @Tag(name = "Job Offers", description = "Recruiter endpoints for offer management")
    @PostMapping("/offers")
    public ResponseEntity<UUID> createOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @RequestBody JobOfferRequest request) {
        return ResponseEntity.ok(jobOfferService.createOffer(recruiterId, request));
    }

    @Tag(name = "Job Offers")
    @PostMapping("/offers/{id}/publish")
    public ResponseEntity<Void> publishOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID id) {
        jobOfferService.publishOffer(recruiterId, id);
        return ResponseEntity.ok().build();
    }

    // --- Matches (Candidate View) ---
    @Tag(name = "Matches (Candidate)", description = "Candidate interactions with matched offers")
    @GetMapping("/candidates/me/matches")
    @Operation(summary = "List matched offers sorted by score")
    public ResponseEntity<List<Object>> getCandidateMatches(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(Collections.singletonList(matchService.getCandidateMatches(candidateId)));
    }

    @Tag(name = "Matches (Candidate)")
    @PostMapping("/candidates/me/matches/{offerId}/interest")
    @Operation(summary = "Mark 'Me interesa' (Triggers profile reveal)")
    public ResponseEntity<Void> markInterest(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID offerId) {
        matchService.markInterest(candidateId, offerId);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Matches (Candidate)")
    @PostMapping("/candidates/me/matches/{offerId}/decline")
    @Operation(summary = "Mark 'No me interesa'")
    public ResponseEntity<Void> declineInterest(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID offerId) {
        matchService.declineMatch(candidateId, offerId);
        return ResponseEntity.ok().build();
    }

    // --- Matches (Recruiter View) ---
    @Tag(name = "Matches (Recruiter)", description = "Recruiter interactions with matched candidates")
    @GetMapping("/offers/{offerId}/candidates")
    @Operation(summary = "List matched candidates for a specific offer")
    public ResponseEntity<List<Object>> getMatchedCandidatesForOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId) {
        return ResponseEntity.ok(Collections.singletonList(matchService.getMatchedCandidatesForOffer(recruiterId, offerId)));
    }

    public record CompanyRequest(String name, String website, String industry, String size, String cultureDescription) {
    }

    public record JobOfferRequest(String title, String modality, String seniority, String description, Double salaryMin,
                                  Double salaryMax, String location) {
    }
}