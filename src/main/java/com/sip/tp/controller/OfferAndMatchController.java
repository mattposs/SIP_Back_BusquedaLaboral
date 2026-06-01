package com.sip.tp.controller;

import com.sip.tp.dto.match.CandidateMatchResponse;
import com.sip.tp.dto.match.MatchDetailResponse;
import com.sip.tp.dto.match.RecruiterCandidateMatchResponse;
import com.sip.tp.dto.request.CompanyRequest;
import com.sip.tp.dto.request.JobOfferRequest;
import com.sip.tp.dto.response.CompanyResponse;
import com.sip.tp.dto.response.JobOfferDetailResponse;
import com.sip.tp.dto.response.JobOfferResponse;
import com.sip.tp.service.domain.matching.MatchFlowService;
import com.sip.tp.service.domain.recruiter.CompanyService;
import com.sip.tp.service.domain.recruiter.JobOfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OfferAndMatchController {

    private final CompanyService companyService;
    private final JobOfferService jobOfferService;
    private final MatchFlowService matchService;

    @Tag(name = "Company", description = "Company profile management")
    @PostMapping("/companies")
    @Operation(summary = "Create a company (during recruiter onboarding)")
    public ResponseEntity<UUID> createCompany(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @RequestBody CompanyRequest request) {
        return ResponseEntity.ok(companyService.createCompany(recruiterId, request));
    }

    @Tag(name = "Company")
    @PutMapping("/companies/{id}")
    @Operation(summary = "Update company info")
    public ResponseEntity<Void> updateCompany(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID id,
            @RequestBody CompanyRequest request) {
        companyService.updateCompany(recruiterId, id, request);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Company")
    @GetMapping("/companies/{id}")
    @Operation(summary = "Get company profile")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @Tag(name = "Job Offers", description = "Recruiter endpoints for offer management")
    @PostMapping("/offers")
    @Operation(summary = "Create a new job offer")
    public ResponseEntity<UUID> createOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @RequestBody JobOfferRequest request) {
        return ResponseEntity.ok(jobOfferService.createOffer(recruiterId, request));
    }

    @Tag(name = "Job Offers")
    @GetMapping("/offers")
    @Operation(summary = "List own offers (recruiter)")
    public ResponseEntity<List<JobOfferResponse>> listOffers(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId) {
        return ResponseEntity.ok(jobOfferService.getOffersByRecruiter(recruiterId));
    }

    @Tag(name = "Job Offers")
    @GetMapping("/offers/{id}")
    @Operation(summary = "Get offer detail")
    public ResponseEntity<JobOfferDetailResponse> getOffer(@PathVariable UUID id) {
        return ResponseEntity.ok(jobOfferService.getOfferById(id));
    }

    @Tag(name = "Job Offers")
    @PutMapping("/offers/{id}")
    @Operation(summary = "Update offer")
    public ResponseEntity<Void> updateOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID id,
            @RequestBody JobOfferRequest request) {
        jobOfferService.updateOffer(recruiterId, id, request);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Job Offers")
    @PostMapping("/offers/{id}/publish")
    @Operation(summary = "Publish a draft offer")
    public ResponseEntity<Void> publishOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID id) {
        jobOfferService.publishOffer(recruiterId, id);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Matches (Candidate)", description = "Candidate interactions with matched offers")
    @GetMapping("/candidates/me/matches")
    @Operation(summary = "List matched offers sorted by score")
    public ResponseEntity<List<CandidateMatchResponse>> getCandidateMatches(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(matchService.getCandidateMatches(candidateId));
    }

    @Tag(name = "Matches (Candidate)")
    @GetMapping("/candidates/me/matches/{offerId}")
    @Operation(summary = "Match detail with matching/missing skills breakdown")
    public ResponseEntity<MatchDetailResponse> getMatchDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID offerId) {
        return ResponseEntity.ok(matchService.getMatchDetail(candidateId, offerId));
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

    @Tag(name = "Matches (Recruiter)", description = "Recruiter interactions with matched candidates")
    @GetMapping("/offers/{offerId}/candidates")
    @Operation(summary = "List matched candidates for a specific offer")
    public ResponseEntity<List<RecruiterCandidateMatchResponse>> getMatchedCandidatesForOffer(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId) {
        return ResponseEntity.ok(matchService.getMatchedCandidatesForOffer(recruiterId, offerId));
    }

    @Tag(name = "Matches (Recruiter)")
    @GetMapping("/offers/{offerId}/candidates/{candidateId}")
    @Operation(summary = "Candidate detail (full if revealed, limited if not)")
    public ResponseEntity<RecruiterCandidateMatchResponse> getCandidateDetailForRecruiter(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId,
            @PathVariable UUID candidateId) {
        return ResponseEntity.ok(matchService.getCandidateDetailForRecruiter(recruiterId, offerId, candidateId));
    }
}
