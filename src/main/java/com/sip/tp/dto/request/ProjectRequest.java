package com.sip.tp.dto.request;

/**
 * DTO for adding project. Consolidated from CandidateController inner class
 */
public record ProjectRequest(
        String title,
        String description,
        String link
) {
}

