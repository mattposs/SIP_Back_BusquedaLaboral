package com.sip.tp.service.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileCompletionCalculatorTest {

    private final ProfileCompletionCalculator calculator = new ProfileCompletionCalculator();

    @Test
    void returnsZeroWhenNoSignalsArePresent() {
        ProfileCompletionCalculator.ProfileSignals signals = new ProfileCompletionCalculator.ProfileSignals(
                false, false, false, 0, 0, false, false
        );

        assertEquals(0, calculator.calculate(signals));
    }

    @Test
    void returnsHundredWhenAllSignalsArePresent() {
        ProfileCompletionCalculator.ProfileSignals signals = new ProfileCompletionCalculator.ProfileSignals(
                true, true, true, 3, 1, true, true
        );

        assertEquals(100, calculator.calculate(signals));
    }
}

