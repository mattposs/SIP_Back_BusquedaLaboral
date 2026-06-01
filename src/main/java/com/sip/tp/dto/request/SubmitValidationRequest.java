package com.sip.tp.dto.request;

/**
 * DTO for submitting validation. Consolidated from model/
 */
public record SubmitValidationRequest(
        java.util.UUID requestId,
        String assignedLevel,
        String comment
) {
}

