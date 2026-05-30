package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Modality;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalityConverter implements AttributeConverter<Modality, String> {
    @Override
    public String convertToDatabaseColumn(Modality attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public Modality convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "REMOTE" -> new Modality.Remote();
            case "HYBRID" -> new Modality.Hybrid();
            case "ONSITE" -> new Modality.OnSite();
            default -> throw new IllegalArgumentException("Unknown Modality: " + dbData);
        };
    }
}

