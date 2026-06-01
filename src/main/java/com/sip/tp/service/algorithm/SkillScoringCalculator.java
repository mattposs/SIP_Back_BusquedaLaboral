package com.sip.tp.service.algorithm;

import com.sip.tp.types.definition.ReputationLevel;
import com.sip.tp.types.definition.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SkillScoringCalculator {

    private final ValidatorReputationCalculator reputationCalculator;

    public ConsolidatedSkillScore calculateConsolidatedScore(List<ValidationInput> validations) {
        if (validations.isEmpty()) {
            throw new IllegalArgumentException("Validations list cannot be empty");
        }

        double totalWeightedScore = 0.0;
        double totalWeights = 0.0;

        for (ValidationInput validation : validations) {
            double weight = reputationCalculator.getReputationWeight(validation.reputationLevel());
            double baseValue = getSkillLevelBaseValue(validation.assignedLevel());
            totalWeightedScore += baseValue * weight;
            totalWeights += weight;
        }

        double finalScore = totalWeightedScore / totalWeights;
        return new ConsolidatedSkillScore(
                BigDecimal.valueOf(finalScore).setScale(2, RoundingMode.HALF_UP),
                deriveLevelFromScore(finalScore)
        );
    }

    private double getSkillLevelBaseValue(SkillLevel level) {
        return switch (level) {
            case SkillLevel.Colaborador() -> 2.5;
            case SkillLevel.EjecutorAutonomo() -> 5.0;
            case SkillLevel.Lider() -> 7.5;
            case SkillLevel.Referente() -> 10.0;
        };
    }

    private SkillLevel deriveLevelFromScore(double score) {
        if (score >= 8.5) return new SkillLevel.Referente();
        if (score >= 6.0) return new SkillLevel.Lider();
        if (score >= 3.5) return new SkillLevel.EjecutorAutonomo();
        return new SkillLevel.Colaborador();
    }

    public record ValidationInput(SkillLevel assignedLevel, ReputationLevel reputationLevel) {
    }

    public record ConsolidatedSkillScore(BigDecimal score, SkillLevel level) {
    }
}

