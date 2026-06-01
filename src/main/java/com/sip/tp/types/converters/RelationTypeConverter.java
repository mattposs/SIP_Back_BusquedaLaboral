package com.sip.tp.types.converters;

import com.sip.tp.types.definition.RelationType;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RelationTypeConverter extends EnumConverter<RelationType> {

    public RelationTypeConverter() {
        super(RelationType.class);
    }
}
