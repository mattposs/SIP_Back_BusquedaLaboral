package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Seniority;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeniorityConverter extends EnumConverter<Seniority> {

    public SeniorityConverter() {
        super(Seniority.class);
    }
}
