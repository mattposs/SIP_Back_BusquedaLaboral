package com.sip.tp.dto.request;

/**
 * DTO for skill addition. Consolidated from model/
 */
public record AddSkillRequest(
        java.util.UUID skillId,
        String experienceRange
) {
}

