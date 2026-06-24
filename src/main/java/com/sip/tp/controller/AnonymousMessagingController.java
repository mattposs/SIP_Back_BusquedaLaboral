package com.sip.tp.controller;

import com.sip.tp.dto.message.AnonymousThreadDetailResponse;
import com.sip.tp.dto.message.AnonymousThreadResponse;
import com.sip.tp.dto.request.CreateThreadRequest;
import com.sip.tp.dto.request.SendMessageRequest;
import com.sip.tp.service.domain.messaging.AnonymousInteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Anonymous Messaging", description = "Double-blind communication between candidates and recruiters")
public class AnonymousMessagingController {

    private final AnonymousInteractionService messagingService;

    // --- Candidate Endpoints ---
    @PostMapping("/anonymous-threads")
    @Operation(summary = "Candidate creates anonymous thread")
    public ResponseEntity<UUID> createThread(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody @Valid CreateThreadRequest request) {
        return ResponseEntity.ok(messagingService.createThread(candidateId, request.offerId(), request.category(), request.message()));
    }

    @GetMapping("/candidates/me/anonymous-threads")
    @Operation(summary = "Candidate's message inbox")
    public ResponseEntity<List<AnonymousThreadResponse>> getCandidateThreads(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(messagingService.getCandidateThreads(candidateId));
    }

    @GetMapping("/candidates/me/anonymous-threads/{id}")
    @Operation(summary = "Candidate views a specific thread with messages")
    public ResponseEntity<AnonymousThreadDetailResponse> getCandidateThreadDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(messagingService.getThreadDetail(id, candidateId));
    }

    // --- Recruiter Endpoints ---
    @GetMapping("/recruiters/me/anonymous-threads")
    @Operation(summary = "Recruiter sees all anonymous threads across their offers")
    public ResponseEntity<List<AnonymousThreadResponse>> getRecruiterThreads(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId) {
        return ResponseEntity.ok(messagingService.getRecruiterThreads(recruiterId));
    }

    @GetMapping("/offers/{offerId}/anonymous-threads")
    @Operation(summary = "Recruiter sees anonymous threads for their offer")
    public ResponseEntity<List<AnonymousThreadResponse>> getOfferThreads(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId) {
        return ResponseEntity.ok(messagingService.getOfferThreads(recruiterId, offerId));
    }

    @GetMapping("/offers/{offerId}/anonymous-threads/{id}")
    @Operation(summary = "Recruiter views a specific anonymous thread detail (no candidate identity)")
    public ResponseEntity<AnonymousThreadDetailResponse> getOfferThreadDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(messagingService.getThreadDetail(id, recruiterId));
    }

    // --- Shared Action ---
    @PostMapping("/anonymous-threads/{id}/messages")
    @Operation(summary = "Send a message within an existing thread")
    public ResponseEntity<Void> sendMessage(
            @PathVariable UUID id,
            @Parameter(hidden = true) @AuthenticationPrincipal UUID senderId,
            @RequestBody @Valid SendMessageRequest request) {
        messagingService.sendMessage(senderId, id, request.content());
        return ResponseEntity.ok().build();
    }
}