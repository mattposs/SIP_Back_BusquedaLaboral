package com.sip.tp.controller;

import com.sip.tp.service.AnonymousInteractionService;
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
@Tag(name = "Anonymous Messaging", description = "Double-blind communication between candidates and recruiters")
public class AnonymousMessagingController {

    private final AnonymousInteractionService messagingService;

    // --- Candidate Endpoints ---
    @PostMapping("/anonymous-threads")
    @Operation(summary = "Candidate creates anonymous thread")
    public ResponseEntity<UUID> createThread(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody CreateThreadRequest request) {
        return ResponseEntity.ok(messagingService.createThread(candidateId, request.offerId(), request.category(), request.message()));
    }

    @GetMapping("/candidates/me/anonymous-threads")
    @Operation(summary = "Candidate's message inbox")
    public ResponseEntity<List<Object>> getCandidateThreads(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(Collections.singletonList(messagingService.getCandidateThreads(candidateId)));
    }

    // --- Recruiter Endpoints ---
    @GetMapping("/offers/{offerId}/anonymous-threads")
    @Operation(summary = "Recruiter sees anonymous threads for their offer")
    public ResponseEntity<List<Object>> getOfferThreads(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID recruiterId,
            @PathVariable UUID offerId) {
        return ResponseEntity.ok(Collections.singletonList(messagingService.getOfferThreads(recruiterId, offerId)));
    }

    // --- Shared Action ---
    @PostMapping("/anonymous-threads/{id}/messages")
    @Operation(summary = "Send a message within an existing thread")
    public ResponseEntity<Void> sendMessage(
            @PathVariable UUID id,
            @Parameter(hidden = true) @AuthenticationPrincipal UUID senderId,
            @RequestBody SendMessageRequest request) {
        messagingService.sendMessage(senderId, id, request.content());
        return ResponseEntity.ok().build();
    }

    public record CreateThreadRequest(UUID offerId, String category, String message) {
    }

    public record SendMessageRequest(String content) {
    }
}