package com.sip.tp.dto.response;

/**
 * DTO for authentication response. Consolidated from model/
 */
public record AuthResponse(
        String token,
        String userType
) {
}

