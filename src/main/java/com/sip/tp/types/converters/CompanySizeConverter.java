package com.sip.tp.types.converters;

import com.sip.tp.types.definition.CompanySize;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CompanySizeConverter extends EnumConverter<CompanySize> {

    public CompanySizeConverter() {
        super(CompanySize.class);
    }
}
