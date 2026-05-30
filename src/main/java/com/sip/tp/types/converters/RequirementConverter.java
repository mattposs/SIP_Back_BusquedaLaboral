package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Requirement;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RequirementConverter implements AttributeConverter<Requirement, String> {
    @Override
    public String convertToDatabaseColumn(Requirement attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public Requirement convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "REQUIRED" -> new Requirement.Required();
            case "DESIRABLE" -> new Requirement.Desirable();
            default -> throw new IllegalArgumentException("Unknown Requirement: " + dbData);
        };
    }
}

