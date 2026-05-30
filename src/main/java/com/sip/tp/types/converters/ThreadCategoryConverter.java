package com.sip.tp.types.converters;

import com.sip.tp.types.definition.ThreadCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThreadCategoryConverter implements AttributeConverter<ThreadCategory, String> {
    @Override
    public String convertToDatabaseColumn(ThreadCategory attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public ThreadCategory convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "SALARY" -> new ThreadCategory.Salary();
            case "CULTURE" -> new ThreadCategory.Culture();
            case "STACK" -> new ThreadCategory.Stack();
            case "BENEFITS" -> new ThreadCategory.Benefits();
            case "MODALITY" -> new ThreadCategory.Modality();
            case "OTHER" -> new ThreadCategory.Other();
            default -> throw new IllegalArgumentException("Unknown ThreadCategory: " + dbData);
        };
    }
}

