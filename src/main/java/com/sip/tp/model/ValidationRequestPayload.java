package com.sip.tp.model;

import java.util.UUID;

public record ValidationRequestPayload(UUID skillId, UUID validatorId, String relationType, String message) {
}
