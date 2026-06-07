package com.sip.tp.dto.response;

import com.sip.tp.dto.skill.OfferSkillResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record JobOfferDetailResponse(
        UUID id,
        String title,
        String modality,
        String seniority,
        String description,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        String benefits,
        String location,
        String status,
        Instant createdAt,
        List<OfferSkillResponse> skills
) {
}
