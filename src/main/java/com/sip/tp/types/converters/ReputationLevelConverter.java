package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ReputationLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReputationLevelConverter implements AttributeConverter<ReputationLevel, String> {
    @Override
    public String convertToDatabaseColumn(ReputationLevel attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public ReputationLevel convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "BRONCE" -> new ReputationLevel.Bronce();
            case "PLATA" -> new ReputationLevel.Plata();
            case "ORO" -> new ReputationLevel.Oro();
            case "PLATINO" -> new ReputationLevel.Platino();
            default -> throw new IllegalArgumentException("Unknown ReputationLevel: " + dbData);
        };
    }
}

