package com.sip.tp.dto.request;

/**
 * DTO for creating anonymous thread. Consolidated from model/
 */
public record CreateThreadRequest(
        java.util.UUID offerId,
        String category,
        String message
) {
}

