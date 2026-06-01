package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ExperienceRange;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExperienceRangeConverter extends EnumConverter<ExperienceRange> {

    public ExperienceRangeConverter() {
        super(ExperienceRange.class);
    }
}
