package com.sip.tp.dto.request;

/**
 * DTO for candidate profile updates. Consolidated from model/
 */
public record ProfileUpdateRequest(
        String location,
        String currentRole,
        String headline,
        String phone,
        String linkedIn
) {
}

