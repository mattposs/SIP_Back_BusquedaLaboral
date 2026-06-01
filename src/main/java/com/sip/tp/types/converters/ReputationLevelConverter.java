package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ReputationLevel;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReputationLevelConverter extends EnumConverter<ReputationLevel> {

    public ReputationLevelConverter() {
        super(ReputationLevel.class);
    }
}
