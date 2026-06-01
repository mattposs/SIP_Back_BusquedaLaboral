package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SkillType;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkillTypeConverter extends EnumConverter<SkillType> {

    public SkillTypeConverter() {
        super(SkillType.class);
    }
}
