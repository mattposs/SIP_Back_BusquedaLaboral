package com.sip.tp.types.converters;

import com.sip.tp.types.definition.RelationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RelationTypeConverter implements AttributeConverter<RelationType, String> {
    @Override
    public String convertToDatabaseColumn(RelationType attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public RelationType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "NONE" -> new RelationType.None();
            case "CLASSMATE" -> new RelationType.Classmate();
            case "COWORKER" -> new RelationType.Coworker();
            case "TEAMMATE" -> new RelationType.Teammate();
            case "TECHLEAD" -> new RelationType.TechLead();
            case "MANAGER" -> new RelationType.Manager();
            default -> throw new IllegalArgumentException("Unknown RelationType: " + dbData);
        };
    }
}

