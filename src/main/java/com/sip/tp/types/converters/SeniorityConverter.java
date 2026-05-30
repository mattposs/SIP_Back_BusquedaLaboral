package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Seniority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeniorityConverter implements AttributeConverter<Seniority, String> {
    @Override
    public String convertToDatabaseColumn(Seniority attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public Seniority convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "JUNIOR" -> new Seniority.Junior();
            case "SEMI_SENIOR" -> new Seniority.SemiSenior();
            case "SENIOR" -> new Seniority.Senior();
            case "LEAD" -> new Seniority.Lead();
            default -> throw new IllegalArgumentException("Unknown Seniority: " + dbData);
        };
    }
}

