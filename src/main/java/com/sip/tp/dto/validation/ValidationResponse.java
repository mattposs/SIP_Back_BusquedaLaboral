package com.sip.tp.dto.validation;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for validations given/received. Consolidated from ValidationFlowService
 */
public record ValidationResponse(
        UUID id,
        String skillName,
        String candidateName,
        String validatorName,
        String assignedLevel,
        String comment,
        Instant createdAt
) {
}

