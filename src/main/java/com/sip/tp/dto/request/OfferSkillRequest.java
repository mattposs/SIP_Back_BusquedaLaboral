package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OfferSkillRequest(
        @NotNull(message = "Skill ID is required")
        UUID skillId,

        String requirement
) {
}
