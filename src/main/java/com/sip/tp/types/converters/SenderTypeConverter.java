package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SenderType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SenderTypeConverter implements AttributeConverter<SenderType, String> {
    @Override
    public String convertToDatabaseColumn(SenderType attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public SenderType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "CANDIDATE" -> new SenderType.Candidate();
            case "RECRUITER" -> new SenderType.Recruiter();
            default -> throw new IllegalArgumentException("Unknown SenderType: " + dbData);
        };
    }
}

