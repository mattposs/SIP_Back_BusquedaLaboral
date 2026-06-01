package com.sip.tp.dto.validation;

import java.util.UUID;

/**
 * DTO for validation requests. Consolidated from ValidationFlowService
 */
public record ValidationRequestResponse(
        UUID id,
        String skillName,
        String requesterName,
        String relationType,
        String message,
        String status
) {
}

