package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ThreadCategory;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThreadCategoryConverter extends EnumConverter<ThreadCategory> {

    public ThreadCategoryConverter() {
        super(ThreadCategory.class);
    }
}
