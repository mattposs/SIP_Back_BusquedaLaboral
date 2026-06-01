package com.sip.tp.util.converter;

import com.sip.tp.dto.request.CompanyRequest;
import com.sip.tp.types.definition.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class RequestConverterTest {

    private final RequestConverter converter = new RequestConverter();

    @Test
    void mapsUserTypeAndSkillLevelFromStringCodes() {
        assertInstanceOf(UserType.Candidate.class, converter.toUserType("candidate"));
        assertInstanceOf(SkillLevel.Lider.class, converter.toSkillLevel("LIDER"));
    }

    @Test
    void mapsCompanyRequestIntoEntityWithConvertedTypes() {
        CompanyRequest request = new CompanyRequest("Acme", "https://acme.com", "TECH", "11-50", "Culture");

        var company = converter.toCompany(request);

        assertInstanceOf(Industry.Tech.class, company.getIndustry());
        assertInstanceOf(CompanySize.Size11To50.class, company.getSize());
    }

    @Test
    void mapsExperienceRangeCode() {
        assertInstanceOf(ExperienceRange.Years4To6.class, converter.toExperienceRange("4-6 years"));
    }
}

