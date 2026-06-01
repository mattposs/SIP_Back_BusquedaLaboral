package com.sip.tp.util.converter;

import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Company;
import com.sip.tp.types.definition.Industry;
import com.sip.tp.types.definition.UserType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseConverterTest {

    private final ResponseConverter converter = new ResponseConverter();

    @Test
    void mapsAuthResponseFromTokenAndUser() {
        Candidate user = new Candidate();
        user.setUserType(new UserType.Recruiter());

        var response = converter.toAuthResponse("jwt-token", user);

        assertEquals("jwt-token", response.token());
        assertEquals("RECRUITER", response.userType());
    }

    @Test
    void mapsCompanyResponseKeepingNullableFields() {
        Company company = Company.builder()
                .id(UUID.randomUUID())
                .name("Acme")
                .industry(new Industry.Tech())
                .isPartner(false)
                .build();

        var response = converter.toCompanyResponse(company);

        assertEquals("Acme", response.name());
        assertEquals("TECH", response.industry());
    }

    @Test
    void mapsCompletionResponse() {
        var response = converter.toCompletionResponse(80);
        assertEquals(80, response.percentage());
    }
}
