package com.sip.tp.model;

public record AuthResponse(
        String token,
        String userType
) {
}
