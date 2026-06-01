package com.sip.tp.controller;

import com.sip.tp.dto.request.SubmitValidationRequest;
import com.sip.tp.dto.request.ValidationRequestPayload;
import com.sip.tp.dto.validation.ValidationRequestResponse;
import com.sip.tp.dto.validation.ValidationResponse;
import com.sip.tp.service.domain.validation.ValidationFlowService;
import com.sip.tp.service.domain.validation.ValidatorSuggestionService;
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
@Tag(name = "Validations", description = "Peer validation system endpoints")
public class ValidationController {

    private final ValidationFlowService validationService;
    private final ValidatorSuggestionService suggestionService;

    @GetMapping("/candidates/me/validations/given")
    @Operation(summary = "Validations I gave to others")
    public ResponseEntity<List<ValidationResponse>> getGivenValidations(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(validationService.getGivenValidations(candidateId));
    }

    @GetMapping("/candidates/me/validations/received")
    @Operation(summary = "Validations I received")
    public ResponseEntity<List<ValidationResponse>> getReceivedValidations(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(validationService.getReceivedValidations(candidateId));
    }

    @GetMapping("/candidates/me/validation-requests")
    @Operation(summary = "Incoming requests asking me to validate others")
    public ResponseEntity<List<ValidationRequestResponse>> getIncomingRequests(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestParam(required = false, defaultValue = "PENDING") String status) {
        return ResponseEntity.ok(validationService.getIncomingRequests(candidateId, status));
    }

    @GetMapping("/candidates/me/validation-requests/sent")
    @Operation(summary = "Outgoing validation requests I sent")
    public ResponseEntity<List<ValidationRequestResponse>> getSentRequests(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(validationService.getSentRequests(candidateId, status));
    }

    @PostMapping("/candidates/me/validation-requests")
    @Operation(summary = "Create a validation request")
    public ResponseEntity<Void> requestValidation(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody ValidationRequestPayload payload) {
        validationService.createValidationRequest(candidateId, payload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/skills/{skillId}/suggested-validators")
    @Operation(summary = "Get suggested validators for a specific skill")
    public ResponseEntity<List<ValidatorSuggestionService.SuggestedValidator>> getSuggestedValidators(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID skillId) {
        return ResponseEntity.ok(suggestionService.suggestValidatorsForSkill(candidateId, skillId));
    }

    @PostMapping("/validations")
    @Operation(summary = "Submit a validation (Accept request)")
    public ResponseEntity<Void> submitValidation(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID validatorId,
            @RequestBody SubmitValidationRequest request) {
        validationService.submitValidation(validatorId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validation-requests/{id}/reject")
    @Operation(summary = "Reject a validation request")
    public ResponseEntity<Void> rejectRequest(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID validatorId,
            @PathVariable UUID id) {
        validationService.rejectValidationRequest(validatorId, id);
        return ResponseEntity.ok().build();
    }
}
