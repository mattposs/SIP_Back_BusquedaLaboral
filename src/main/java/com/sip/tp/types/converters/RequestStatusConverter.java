package com.sip.tp.types.converters;

import com.sip.tp.types.definition.RequestStatus;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RequestStatusConverter extends EnumConverter<RequestStatus> {

    public RequestStatusConverter() {
        super(RequestStatus.class);
    }
}
