package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Industry;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndustryConverter implements AttributeConverter<Industry, String> {
    @Override
    public String convertToDatabaseColumn(Industry attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public Industry convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "TECH" -> new Industry.Tech();
            case "FINANCE" -> new Industry.Finance();
            case "ECOMMERCE" -> new Industry.Ecommerce();
            case "CONSULTING" -> new Industry.Consulting();
            case "OTHER" -> new Industry.Other();
            default -> throw new IllegalArgumentException("Unknown Industry: " + dbData);
        };
    }
}

