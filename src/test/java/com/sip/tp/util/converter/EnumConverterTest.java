package com.sip.tp.util.converter;

import com.sip.tp.types.converters.ExperienceRangeConverter;
import com.sip.tp.types.definition.ExperienceRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumConverterTest {

    private final ExperienceRangeConverter converter = new ExperienceRangeConverter();

    @Test
    void convertsTypeToDatabaseCodeAndBack() {
        String dbValue = converter.convertToDatabaseColumn(new ExperienceRange.Years4To6());
        ExperienceRange restored = converter.convertToEntityAttribute(dbValue);

        assertEquals("4-6 years", dbValue);
        assertInstanceOf(ExperienceRange.Years4To6.class, restored);
    }

    @Test
    void throwsOnUnknownCode() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("UNKNOWN"));
    }
}

