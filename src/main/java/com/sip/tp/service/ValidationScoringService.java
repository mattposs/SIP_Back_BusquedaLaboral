package com.sip.tp.service;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Validation;
import com.sip.tp.entity.ValidatorReputation;
import com.sip.tp.repository.ValidatorReputationRepository;
import com.sip.tp.types.definition.ReputationLevel;
import com.sip.tp.types.definition.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationScoringService {

    // Inject the repository to fetch the reputation independently
    private final ValidatorReputationRepository reputationRepository;

    /**
     * Algorithm 2.3: Validator Reputation Score
     * Formula: (experiencePts x 0.5) + (historyPts x 0.3) + (relationPts x 0.2) = reputationScore
     */
    public BigDecimal calculateReputationScore(int experiencePts, int historyPts, int relationPts) {
        double score = (experiencePts * 0.5) + (historyPts * 0.3) + (relationPts * 0.2);
        return BigDecimal.valueOf(score).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Maps ReputationLevel to a mathematical weight multiplier.
     */
    private double getReputationWeight(ReputationLevel level) {
        return switch (level) {
            case ReputationLevel.Bronce() -> 1.0;
            case ReputationLevel.Plata() -> 1.5;
            case ReputationLevel.Oro() -> 2.0;
            case ReputationLevel.Platino() -> 3.0;
        };
    }

    /**
     * Maps the SkillLevel ADT to a numerical base value.
     */
    private double getSkillLevelBaseValue(SkillLevel level) {
        return switch (level) {
            case SkillLevel.Colaborador() -> 2.5;
            case SkillLevel.EjecutorAutonomo() -> 5.0;
            case SkillLevel.Lider() -> 7.5;
            case SkillLevel.Referente() -> 10.0;
        };
    }

    /**
     * Algorithm 2.2: Skill Consolidated Score
     * Calculates the weighted average of all validations for a specific skill.
     */
    @Transactional
    public void updateConsolidatedScore(CandidateSkill candidateSkill, List<Validation> validations) {
        if (validations.isEmpty()) return;

        double totalWeightedScore = 0.0;
        double totalWeights = 0.0;

        for (Validation validation : validations) {
            // CORRECTED: Fetch reputation via repository using the validator's ID
            ValidatorReputation reputation = reputationRepository.findById(validation.getValidator().getId())
                    .orElseThrow(() -> new IllegalStateException("Reputation profile not found for validator ID: " + validation.getValidator().getId()));

            double weight = getReputationWeight(reputation.getReputationLevel());
            double baseValue = getSkillLevelBaseValue(validation.getAssignedLevel());

            totalWeightedScore += (baseValue * weight);
            totalWeights += weight;
        }

        double finalScore = totalWeightedScore / totalWeights;
        candidateSkill.setConsolidatedScore(BigDecimal.valueOf(finalScore).setScale(2, RoundingMode.HALF_UP));
        candidateSkill.setConsolidatedLevel(deriveLevelFromScore(finalScore));
    }

    private SkillLevel deriveLevelFromScore(double score) {
        if (score >= 8.5) return new SkillLevel.Referente();
        if (score >= 6.0) return new SkillLevel.Lider();
        if (score >= 3.5) return new SkillLevel.EjecutorAutonomo();
        return new SkillLevel.Colaborador();
    }
}