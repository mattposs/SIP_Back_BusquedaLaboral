package com.sip.tp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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