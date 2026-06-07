package com.sip.tp.dto.request;

import java.util.List;

public record JobOfferRequest(
        String title,
        String modality,
        String seniority,
        String description,
        Double salaryMin,
        Double salaryMax,
        String location,
        String benefits,
        List<OfferSkillRequest> skills
) {
}
