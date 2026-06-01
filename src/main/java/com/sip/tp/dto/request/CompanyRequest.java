package com.sip.tp.dto.request;

/**
 * DTO for company creation/update. Consolidated from OfferAndMatchController inner class
 */
public record CompanyRequest(
        String name,
        String website,
        String industry,
        String size,
        String cultureDescription
) {
}

