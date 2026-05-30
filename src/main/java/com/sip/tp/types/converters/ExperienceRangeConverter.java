package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ExperienceRange;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExperienceRangeConverter implements AttributeConverter<ExperienceRange, String> {
    @Override
    public String convertToDatabaseColumn(ExperienceRange attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public ExperienceRange convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "<1 year" -> new ExperienceRange.LessThan1Year();
            case "1-3 years" -> new ExperienceRange.Years1To3();
            case "4-6 years" -> new ExperienceRange.Years4To6();
            case "7-10 years" -> new ExperienceRange.Years7To10();
            case "10+ years" -> new ExperienceRange.Years10Plus();
            default -> throw new IllegalArgumentException("Unknown ExperienceRange: " + dbData);
        };
    }
}

