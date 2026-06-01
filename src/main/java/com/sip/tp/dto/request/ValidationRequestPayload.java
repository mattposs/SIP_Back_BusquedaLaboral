package com.sip.tp.dto.request;

/**
 * DTO for creating validation request. Consolidated from model/
 */
public record ValidationRequestPayload(
        java.util.UUID validatorId,
        java.util.UUID skillId,
        String relationType,
        String message
) {
}

