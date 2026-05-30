package com.sip.tp.model;

import java.util.UUID;

public record CreateThreadRequest(UUID offerId, String category, String message) {
}
