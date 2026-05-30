package com.sip.tp.model;

public record ProfileUpdateRequest(String location, String currentRole, String headline, String phone,
                                   String linkedIn) {
}
