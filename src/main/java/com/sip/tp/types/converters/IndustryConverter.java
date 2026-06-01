package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Industry;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndustryConverter extends EnumConverter<Industry> {

    public IndustryConverter() {
        super(Industry.class);
    }
}
