package com.sip.tp.service.algorithm;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.OfferSkill;
import com.sip.tp.entity.OfferSkillId;
import com.sip.tp.entity.Skill;
import com.sip.tp.types.definition.Requirement;
import com.sip.tp.types.definition.SkillLevel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchScoreCalculatorTest {

    private final MatchScoreCalculator calculator = new MatchScoreCalculator();

    @Test
    void returnsHundredWhenOfferHasNoSkillRequirements() {
        int score = calculator.calculateMatchScore(List.of(), List.of());

        assertEquals(100, score);
    }

    @Test
    void computesWeightedMatchScoreWithLevelBonus() {
        UUID javaSkillId = UUID.randomUUID();
        UUID sqlSkillId = UUID.randomUUID();

        Skill java = Skill.builder().id(javaSkillId).name("Java").build();
        Skill sql = Skill.builder().id(sqlSkillId).name("SQL").build();

        CandidateSkill candidateJava = CandidateSkill.builder()
                .skill(java)
                .consolidatedLevel(new SkillLevel.Lider())
                .build();

        OfferSkill requiredJava = OfferSkill.builder()
                .id(new OfferSkillId(UUID.randomUUID(), javaSkillId))
                .skill(java)
                .requirement(new Requirement.Required())
                .build();

        OfferSkill desirableSql = OfferSkill.builder()
                .id(new OfferSkillId(UUID.randomUUID(), sqlSkillId))
                .skill(sql)
                .requirement(new Requirement.Desirable())
                .build();

        int score = calculator.calculateMatchScore(List.of(candidateJava), List.of(requiredJava, desirableSql));

        assertEquals(64, score);
    }
}
