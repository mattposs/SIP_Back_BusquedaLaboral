package com.sip.tp.service.algorithm;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.OfferSkill;
import com.sip.tp.types.definition.Requirement;
import com.sip.tp.types.definition.SkillLevel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MatchScoreCalculator {

    public int calculateMatchScore(List<CandidateSkill> candidateSkills, List<OfferSkill> offerSkills) {
        if (offerSkills.isEmpty()) {
            return 100;
        }

        Map<UUID, CandidateSkill> candidateSkillMap = candidateSkills.stream()
                .collect(Collectors.toMap(cs -> cs.getSkill().getId(), cs -> cs));

        double totalPossiblePoints = 0.0;
        double earnedPoints = 0.0;

        for (OfferSkill req : offerSkills) {
            double weight = req.getRequirement() instanceof Requirement.Required() ? 2.0 : 1.0;
            totalPossiblePoints += weight;

            CandidateSkill candidateSkill = candidateSkillMap.get(req.getSkill().getId());
            if (candidateSkill == null) {
                continue;
            }

            earnedPoints += weight;
            if (candidateSkill.getConsolidatedLevel() != null) {
                earnedPoints += calculateLevelBonus(candidateSkill.getConsolidatedLevel(), weight);
            }
        }

        double rawPercentage = (earnedPoints / (totalPossiblePoints * 1.25)) * 100;
        return Math.min(100, (int) Math.round(rawPercentage));
    }

    private double calculateLevelBonus(SkillLevel level, double baseWeight) {
        return switch (level) {
            case SkillLevel.Colaborador() -> baseWeight * 0.05;
            case SkillLevel.EjecutorAutonomo() -> baseWeight * 0.10;
            case SkillLevel.Lider() -> baseWeight * 0.20;
            case SkillLevel.Referente() -> baseWeight * 0.25;
        };
    }
}

