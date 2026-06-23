package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for company creation/update. Consolidated from OfferAndMatchController inner class
 */
public record CompanyRequest(
        @NotBlank(message = "Company name is required")
        String name,

        String website,

        @NotBlank(message = "Industry is required")
        String industry,

        @NotBlank(message = "Company size is required")
        String size,

        String cultureDescription
) {
}

