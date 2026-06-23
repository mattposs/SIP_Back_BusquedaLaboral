package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for identity verification. Consolidated from model/
 */
public record VerifyIdentityRequest(
        @NotBlank(message = "DNI is required")
        String dni,

        @NotBlank(message = "Tramite number is required")
        String tramiteNumber
) {
}

