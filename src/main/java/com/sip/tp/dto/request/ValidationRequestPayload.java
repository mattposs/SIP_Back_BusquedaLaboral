package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO for creating validation request. Consolidated from model/
 */
public record ValidationRequestPayload(
        @NotNull(message = "Validator ID is required")
        UUID validatorId,

        @NotNull(message = "Skill ID is required")
        UUID skillId,

        @NotBlank(message = "Relation type is required")
        String relationType,

        String message
) {
}

