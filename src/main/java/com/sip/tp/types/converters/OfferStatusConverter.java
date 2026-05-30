package com.sip.tp.types.converters;

import com.sip.tp.types.definition.OfferStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OfferStatusConverter implements AttributeConverter<OfferStatus, String> {
    @Override
    public String convertToDatabaseColumn(OfferStatus attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public OfferStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "DRAFT" -> new OfferStatus.Draft();
            case "PUBLISHED" -> new OfferStatus.Published();
            case "CLOSED" -> new OfferStatus.Closed();
            default -> throw new IllegalArgumentException("Unknown OfferStatus: " + dbData);
        };
    }
}

