package com.sip.tp.types.converters;

import com.sip.tp.types.definition.MatchStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MatchStatusConverter implements AttributeConverter<MatchStatus, String> {
    @Override
    public String convertToDatabaseColumn(MatchStatus attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public MatchStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "SUGGESTED" -> new MatchStatus.Suggested();
            case "INTERESTED" -> new MatchStatus.Interested();
            case "NOT_INTERESTED" -> new MatchStatus.NotInterested();
            default -> throw new IllegalArgumentException("Unknown MatchStatus: " + dbData);
        };
    }
}

