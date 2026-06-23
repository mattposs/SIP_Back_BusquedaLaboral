package com.sip.tp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO for creating anonymous thread. Consolidated from model/
 */
public record CreateThreadRequest(
        @NotNull(message = "Offer ID is required")
        UUID offerId,

        @NotBlank(message = "Category is required")
        String category,

        @NotBlank(message = "Message is required")
        String message
) {
}

