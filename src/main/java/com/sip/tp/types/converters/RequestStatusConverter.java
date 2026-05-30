package com.sip.tp.types.converters;

import com.sip.tp.types.definition.RequestStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {
    @Override
    public String convertToDatabaseColumn(RequestStatus attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public RequestStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "PENDING" -> new RequestStatus.Pending();
            case "COMPLETED" -> new RequestStatus.Completed();
            case "REJECTED" -> new RequestStatus.Rejected();
            default -> throw new IllegalArgumentException("Unknown RequestStatus: " + dbData);
        };
    }
}

