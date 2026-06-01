package com.sip.tp.service.algorithm;

import com.sip.tp.types.definition.ReputationLevel;
import com.sip.tp.types.definition.SkillLevel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SkillScoringCalculatorTest {

    private final SkillScoringCalculator calculator = new SkillScoringCalculator(new ValidatorReputationCalculator());

    @Test
    void calculatesWeightedAverageAndDerivedLevel() {
        List<SkillScoringCalculator.ValidationInput> validations = List.of(
                new SkillScoringCalculator.ValidationInput(new SkillLevel.Referente(), new ReputationLevel.Platino()),
                new SkillScoringCalculator.ValidationInput(new SkillLevel.Colaborador(), new ReputationLevel.Bronce())
        );

        SkillScoringCalculator.ConsolidatedSkillScore result = calculator.calculateConsolidatedScore(validations);

        assertEquals("8.13", result.score().toPlainString());
        assertInstanceOf(SkillLevel.Lider.class, result.level());
    }

    @Test
    void mapsBoundaryScoresToExpectedLevel() {
        List<SkillScoringCalculator.ValidationInput> validations = List.of(
                new SkillScoringCalculator.ValidationInput(new SkillLevel.Lider(), new ReputationLevel.Oro())
        );

        SkillScoringCalculator.ConsolidatedSkillScore result = calculator.calculateConsolidatedScore(validations);

        assertEquals("7.50", result.score().toPlainString());
        assertInstanceOf(SkillLevel.Lider.class, result.level());
    }
}

