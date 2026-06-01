package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Requirement;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RequirementConverter extends EnumConverter<Requirement> {

    public RequirementConverter() {
        super(Requirement.class);
    }
}
