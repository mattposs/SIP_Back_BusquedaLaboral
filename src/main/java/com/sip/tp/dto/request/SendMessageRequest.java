package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for sending message. Consolidated from model/
 */
public record SendMessageRequest(
        @NotBlank(message = "Message content is required")
        String content
) {
}

