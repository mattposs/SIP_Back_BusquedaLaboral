package com.sip.tp.types.converters;

import com.sip.tp.types.definition.UserType;
import com.sip.tp.util.converter.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter extends EnumConverter<UserType> {

    public UserTypeConverter() {
        super(UserType.class);
    }
}