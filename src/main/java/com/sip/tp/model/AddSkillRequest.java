package com.sip.tp.model;

import java.util.UUID;

public record AddSkillRequest(UUID skillId, String experienceRange) {
}
