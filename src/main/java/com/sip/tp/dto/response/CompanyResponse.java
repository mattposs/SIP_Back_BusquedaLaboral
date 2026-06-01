package com.sip.tp.dto.response;

import java.util.UUID;

/**
 * DTO for company response. Consolidated from CompanyService
 */
public record CompanyResponse(
        UUID id,
        String name,
        String logo,
        String website,
        String industry,
        String size,
        String cultureDescription,
        Boolean isPartner
) {
}

