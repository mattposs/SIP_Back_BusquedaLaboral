package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for adding project. Consolidated from CandidateController inner class
 */
public record ProjectRequest(
        @NotBlank(message = "Project title is required")
        String title,

        String description,
        String link
) {
}

