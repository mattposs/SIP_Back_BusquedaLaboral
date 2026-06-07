package com.sip.tp.dto.request;

import java.util.UUID;

public record OfferSkillRequest(
        UUID skillId,
        String requirement
) {
}
