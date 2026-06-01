package com.sip.tp.service.domain.matching;

import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.JobOffer;
import com.sip.tp.entity.OfferSkill;
import com.sip.tp.service.algorithm.MatchScoreCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchCalculationService {

    private final MatchScoreCalculator matchScoreCalculator;

    public int calculateMatchScore(List<CandidateSkill> candidateSkills, JobOffer offer, List<OfferSkill> offerSkills) {
        return matchScoreCalculator.calculateMatchScore(candidateSkills, offerSkills);
    }
}
