package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SenderType;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SenderTypeConverter extends EnumConverter<SenderType> {

    public SenderTypeConverter() {
        super(SenderType.class);
    }
}
