package com.sip.tp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for user registration. Consolidated from model/ and controller/
 */
public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Must be a valid email format")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "User type is required")
        String userType, // "CANDIDATE" or "RECRUITER"

        @NotBlank(message = "Full name is required")
        String fullName
) {
}

