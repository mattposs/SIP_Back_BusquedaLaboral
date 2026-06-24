package com.sip.tp.dto.response;

import java.util.UUID;

/**
 * DTO for candidate profile. Consolidated from CandidateProfileService
 */
public record CandidateProfileResponse(
        UUID id,
        String fullName,
        String email,
        String location,
        String currentRole,
        String headline,
        String profilePhoto,
        String phone,
        String linkedIn,
        Boolean identityVerified,
        Integer profileCompletion
) {
}

