package com.sip.tp.types.converters;

import com.sip.tp.types.definition.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {
    @Override
    public String convertToDatabaseColumn(UserType attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public UserType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "CANDIDATE" -> new UserType.Candidate();
            case "RECRUITER" -> new UserType.Recruiter();
            default -> throw new IllegalArgumentException("Unknown UserType: " + dbData);
        };
    }
}