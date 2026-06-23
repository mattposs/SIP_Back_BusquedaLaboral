package com.sip.tp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record JobOfferRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Modality is required")
        String modality,

        @NotBlank(message = "Seniority is required")
        String seniority,

        String description,
        Double salaryMin,
        Double salaryMax,
        String location,
        String benefits,

        @Valid
        List<OfferSkillRequest> skills
) {
}
