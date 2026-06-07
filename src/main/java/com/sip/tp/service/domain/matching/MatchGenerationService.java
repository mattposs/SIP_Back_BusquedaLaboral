package com.sip.tp.service.domain.matching;

import com.sip.tp.dto.match.MatchingSkillResponse;
import com.sip.tp.dto.match.MissingSkillResponse;
import com.sip.tp.entity.*;
import com.sip.tp.repository.*;
import com.sip.tp.service.algorithm.MatchScoreCalculator;
import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.types.definition.OfferStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchGenerationService {

    private final MatchRepository matchRepository;
    private final OfferSkillRepository offerSkillRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final CandidateRepository candidateRepository;
    private final JobOfferRepository offerRepository;
    private final MatchScoreCalculator matchScoreCalculator;

    @Transactional
    public void generateMatchesForOffer(UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        List<OfferSkill> offerSkills = offerSkillRepository.findAllByOffer_Id(offerId);

        matchRepository.findAllByOfferIdOrderByMatchScoreDesc(offerId)
                .forEach(matchRepository::delete);

        for (Candidate candidate : candidateRepository.findAll()) {
            List<CandidateSkill> candidateSkills = candidateSkillRepository.findAllByCandidateId(candidate.getId());
            int score = matchScoreCalculator.calculateMatchScore(candidateSkills, offerSkills);
            if (score <= 0 && !offerSkills.isEmpty()) {
                continue;
            }

            Optional<Match> existing = matchRepository.findByCandidateIdAndOfferId(candidate.getId(), offerId);
            Match match = existing.orElseGet(() -> Match.builder()
                    .candidate(candidate)
                    .offer(offer)
                    .profileRevealed(false)
                    .build());
            match.setMatchScore(score);
            if (match.getStatus() == null) {
                match.setStatus(new MatchStatus.Suggested());
            }
            matchRepository.save(match);
        }
    }

    @Transactional
    public void recomputeMatchesForCandidate(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findAllByCandidateId(candidateId);

        List<JobOffer> publishedOffers = offerRepository.findAll().stream()
                .filter(offer -> offer.getStatus() instanceof OfferStatus.Published)
                .toList();

        for (JobOffer offer : publishedOffers) {
            List<OfferSkill> offerSkills = offerSkillRepository.findAllByOffer_Id(offer.getId());
            int score = matchScoreCalculator.calculateMatchScore(candidateSkills, offerSkills);
            if (score <= 0 && !offerSkills.isEmpty()) {
                matchRepository.findByCandidateIdAndOfferId(candidateId, offer.getId())
                        .ifPresent(matchRepository::delete);
                continue;
            }

            Optional<Match> existing = matchRepository.findByCandidateIdAndOfferId(candidateId, offer.getId());
            Match match = existing.orElseGet(() -> Match.builder()
                    .candidate(candidate)
                    .offer(offer)
                    .profileRevealed(false)
                    .status(new MatchStatus.Suggested())
                    .build());
            match.setMatchScore(score);
            matchRepository.save(match);
        }
    }

    @Transactional(readOnly = true)
    public List<MatchingSkillResponse> getMatchingSkills(UUID candidateId, UUID offerId) {
        Map<UUID, CandidateSkill> candidateSkillMap = candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .collect(Collectors.toMap(cs -> cs.getSkill().getId(), cs -> cs));

        return offerSkillRepository.findAllByOffer_Id(offerId).stream()
                .filter(os -> candidateSkillMap.containsKey(os.getSkill().getId()))
                .map(os -> {
                    CandidateSkill cs = candidateSkillMap.get(os.getSkill().getId());
                    return new MatchingSkillResponse(
                            os.getSkill().getName(),
                            cs.getExperienceRange().code(),
                            cs.getConsolidatedLevel() != null ? cs.getConsolidatedLevel().code() : null
                    );
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MissingSkillResponse> getMissingSkills(UUID candidateId, UUID offerId) {
        Set<UUID> candidateSkillIds = candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .map(cs -> cs.getSkill().getId())
                .collect(Collectors.toSet());

        return offerSkillRepository.findAllByOffer_Id(offerId).stream()
                .filter(os -> !candidateSkillIds.contains(os.getSkill().getId()))
                .map(os -> new MissingSkillResponse(os.getSkill().getName(), os.getRequirement().code()))
                .toList();
    }
}
