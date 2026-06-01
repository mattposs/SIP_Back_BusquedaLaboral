package com.sip.tp.types.converters;

import com.sip.tp.types.definition.Modality;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalityConverter extends EnumConverter<Modality> {

    public ModalityConverter() {
        super(Modality.class);
    }
}
