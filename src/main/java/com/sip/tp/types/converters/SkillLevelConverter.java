package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SkillLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkillLevelConverter implements AttributeConverter<SkillLevel, String> {
    @Override
    public String convertToDatabaseColumn(SkillLevel attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public SkillLevel convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "COLABORADOR" -> new SkillLevel.Colaborador();
            case "EJECUTOR_AUTONOMO" -> new SkillLevel.EjecutorAutonomo();
            case "LIDER" -> new SkillLevel.Lider();
            case "REFERENTE" -> new SkillLevel.Referente();
            default -> throw new IllegalArgumentException("Unknown SkillLevel: " + dbData);
        };
    }
}

