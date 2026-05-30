package com.sip.tp.model;

import java.util.UUID;

public record SubmitValidationRequest(UUID requestId, String assignedLevel, String comment) {
}
