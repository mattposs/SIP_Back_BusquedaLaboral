package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ThreadStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThreadStatusConverter implements AttributeConverter<ThreadStatus, String> {
    @Override
    public String convertToDatabaseColumn(ThreadStatus attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public ThreadStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "PENDING" -> new ThreadStatus.Pending();
            case "RESPONDED" -> new ThreadStatus.Responded();
            default -> throw new IllegalArgumentException("Unknown ThreadStatus: " + dbData);
        };
    }
}

