package com.sip.tp.service.domain.validation;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Validation;
import com.sip.tp.entity.ValidatorReputation;
import com.sip.tp.repository.ValidatorReputationRepository;
import com.sip.tp.service.algorithm.SkillScoringCalculator;
import com.sip.tp.service.algorithm.ValidatorReputationCalculator;
import com.sip.tp.types.definition.ReputationLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationScoringService {

    private final ValidatorReputationRepository reputationRepository;
    private final ValidatorReputationCalculator reputationCalculator;
    private final SkillScoringCalculator skillScoringCalculator;

    public BigDecimal calculateReputationScore(int experiencePts, int historyPts, int relationPts) {
        return reputationCalculator.calculateReputationScore(experiencePts, historyPts, relationPts);
    }

    @Transactional
    public void updateConsolidatedScore(CandidateSkill candidateSkill, List<Validation> validations) {
        if (validations.isEmpty()) return;

        List<SkillScoringCalculator.ValidationInput> inputs = new ArrayList<>(validations.size());
        for (Validation validation : validations) {
            // TODO acá tendría que existir el usuario en el repositorio de reputación antes de intentar buscarlo
            ValidatorReputation reputation = reputationRepository.findById(validation.getValidator().getId())
                    .orElseThrow(() -> new IllegalStateException("Reputation profile not found for validator ID: " + validation.getValidator().getId()));

            ReputationLevel level = reputation.getReputationLevel();
            inputs.add(new SkillScoringCalculator.ValidationInput(validation.getAssignedLevel(), level));
        }

        SkillScoringCalculator.ConsolidatedSkillScore consolidated = skillScoringCalculator.calculateConsolidatedScore(inputs);
        candidateSkill.setConsolidatedScore(consolidated.score());
        candidateSkill.setConsolidatedLevel(consolidated.level());
    }
}
