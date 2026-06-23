package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO for submitting validation. Consolidated from model/
 */
public record SubmitValidationRequest(
        @NotNull(message = "Request ID is required")
        UUID requestId,

        @NotBlank(message = "Assigned level is required")
        String assignedLevel,

        String comment
) {
}

