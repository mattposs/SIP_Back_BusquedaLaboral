package com.sip.tp.types.converters;

import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MatchStatusConverter extends EnumConverter<MatchStatus> {

    public MatchStatusConverter() {
        super(MatchStatus.class);
    }
}
