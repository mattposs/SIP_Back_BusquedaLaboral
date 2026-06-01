package com.sip.tp.dto.skill;

import java.util.UUID;

/**
 * DTO for skill catalog. Consolidated from SkillService
 */
public record SkillResponse(
        UUID id,
        String name,
        String type
) {
}

