package com.sip.tp.dto.request;

/**
 * DTO for identity verification. Consolidated from model/
 */
public record VerifyIdentityRequest(
        String dni,
        String tramiteNumber
) {
}

