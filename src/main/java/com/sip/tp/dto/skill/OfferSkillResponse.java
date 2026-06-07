package com.sip.tp.dto.skill;

import java.util.UUID;

public record OfferSkillResponse(
        UUID skillId,
        String skillName,
        String skillType,
        String requirement
) {
}
