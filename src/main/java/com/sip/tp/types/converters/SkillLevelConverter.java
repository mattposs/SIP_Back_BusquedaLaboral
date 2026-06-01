package com.sip.tp.types.converters;

import com.sip.tp.types.definition.SkillLevel;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkillLevelConverter extends EnumConverter<SkillLevel> {

    public SkillLevelConverter() {
        super(SkillLevel.class);
    }
}
