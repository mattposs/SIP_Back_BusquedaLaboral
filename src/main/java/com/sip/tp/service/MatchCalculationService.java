package com.sip.tp.service;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.JobOffer;
import com.sip.tp.entity.OfferSkill;
import com.sip.tp.types.definition.Requirement;
import com.sip.tp.types.definition.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchCalculationService {

    /**
     * Algorithm 2.1: Match Score Calculation (0-100%)
     */
    public int calculateMatchScore(List<CandidateSkill> candidateSkills, JobOffer offer, List<OfferSkill> offerSkills) {
        if (offerSkills.isEmpty()) return 100;

        Map<UUID, CandidateSkill> candidateSkillMap = candidateSkills.stream()
                .collect(Collectors.toMap(cs -> cs.getSkill().getId(), cs -> cs));

        double totalPossiblePoints = 0.0;
        double earnedPoints = 0.0;

        for (OfferSkill req : offerSkills) {
            // Required skills weigh heavier than desirable skills
            double weight = req.getRequirement() instanceof Requirement.Required() ? 2.0 : 1.0;
            totalPossiblePoints += weight;

            if (candidateSkillMap.containsKey(req.getSkill().getId())) {
                CandidateSkill cs = candidateSkillMap.get(req.getSkill().getId());

                // Base point for having the skill
                earnedPoints += weight;

                // Bonus points based on consolidated level (if validated)
                if (cs.getConsolidatedLevel() != null) {
                    earnedPoints += calculateLevelBonus(cs.getConsolidatedLevel(), weight);
                }
            }
        }

        double rawPercentage = (earnedPoints / (totalPossiblePoints * 1.25)) * 100; // 1.25 accounts for max bonus
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