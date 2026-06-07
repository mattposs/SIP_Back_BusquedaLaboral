package com.sip.tp.service.domain.auth;

import com.sip.tp.entity.Candidate;
import com.sip.tp.types.definition.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "MySuperSecretKeyForSkillPassportApp2026!");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 86400000L);
    }

    @Test
    void generateToken_includesSubjectAndUserId() {
        UUID userId = UUID.randomUUID();
        Candidate candidate = new Candidate();
        candidate.setId(userId);
        candidate.setEmail("test@example.com");
        candidate.setUserType(new UserType.Candidate());

        String token = jwtService.generateToken(candidate);

        assertEquals("test@example.com", jwtService.extractUsername(token));
        assertEquals(userId, jwtService.extractUserId(token));
        assertNotNull(jwtService.isTokenValid(token));
    }
}
