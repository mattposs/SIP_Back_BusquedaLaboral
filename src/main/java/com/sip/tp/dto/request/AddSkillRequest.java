package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO for skill addition. Consolidated from model/
 */
public record AddSkillRequest(
        @NotNull(message = "Skill ID is required")
        UUID skillId,

        @NotBlank(message = "Experience range is required")
        String experienceRange
) {
}

