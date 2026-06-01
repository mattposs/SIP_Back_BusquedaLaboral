package com.sip.tp.service.algorithm;

import com.sip.tp.types.definition.ReputationLevel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ValidatorReputationCalculator {

    public BigDecimal calculateReputationScore(int experiencePts, int historyPts, int relationPts) {
        double score = (experiencePts * 0.5) + (historyPts * 0.3) + (relationPts * 0.2);
        return BigDecimal.valueOf(score).setScale(2, RoundingMode.HALF_UP);
    }

    public double getReputationWeight(ReputationLevel level) {
        return switch (level) {
            case ReputationLevel.Bronce() -> 1.0;
            case ReputationLevel.Plata() -> 1.5;
            case ReputationLevel.Oro() -> 2.0;
            case ReputationLevel.Platino() -> 3.0;
        };
    }
}

