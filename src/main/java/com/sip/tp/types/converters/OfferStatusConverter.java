package com.sip.tp.types.converters;

import com.sip.tp.types.definition.OfferStatus;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OfferStatusConverter extends EnumConverter<OfferStatus> {

    public OfferStatusConverter() {
        super(OfferStatus.class);
    }
}
