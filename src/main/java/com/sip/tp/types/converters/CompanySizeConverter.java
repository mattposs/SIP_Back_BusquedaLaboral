package com.sip.tp.types.converters;

import com.sip.tp.types.definition.CompanySize;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CompanySizeConverter implements AttributeConverter<CompanySize, String> {
    @Override
    public String convertToDatabaseColumn(CompanySize attribute) {
        return (attribute == null) ? null : attribute.code();
    }

    @Override
    public CompanySize convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "1-10" -> new CompanySize.Size1To10();
            case "11-50" -> new CompanySize.Size11To50();
            case "51-200" -> new CompanySize.Size51To200();
            case "201-1000" -> new CompanySize.Size201To1000();
            case "1000+" -> new CompanySize.Size1000Plus();
            default -> throw new IllegalArgumentException("Unknown CompanySize: " + dbData);
        };
    }
}

