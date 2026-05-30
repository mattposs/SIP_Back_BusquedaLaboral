package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SkillType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkillTypeConverter implements AttributeConverter<SkillType, String> {
    @Override
    public String convertToDatabaseColumn(SkillType attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public SkillType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "TECH" -> new SkillType.Tech();
            case "SOFT" -> new SkillType.Soft();
            default -> throw new IllegalArgumentException("Unknown SkillType: " + dbData);
        };
    }
}

