package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ThreadStatus;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThreadStatusConverter extends EnumConverter<ThreadStatus> {

    public ThreadStatusConverter() {
        super(ThreadStatus.class);
    }
}
