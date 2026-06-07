package com.sip.tp.dto.response;

import java.util.UUID;

public record ProjectResponse(
        UUID id,
        String title,
        String description,
        String link
) {
}
