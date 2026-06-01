package com.sip.tp.service.domain.matching;

import com.sip.tp.dto.match.CandidateMatchResponse;
import com.sip.tp.dto.match.MatchDetailResponse;
import com.sip.tp.dto.match.RecruiterCandidateMatchResponse;
import com.sip.tp.entity.Match;
import com.sip.tp.repository.MatchRepository;
import com.sip.tp.service.domain.messaging.AnonymousInteractionService;
import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchFlowService {

    private final MatchRepository matchRepository;
    private final AnonymousInteractionService interactionService;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public List<CandidateMatchResponse> getCandidateMatches(UUID candidateId) {
        return matchRepository.findAllByCandidateIdOrderByMatchScoreDesc(candidateId).stream()
                .map(responseConverter::toCandidateMatchResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MatchDetailResponse getMatchDetail(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        return responseConverter.toMatchDetailResponse(match);
    }

    @Transactional(readOnly = true)
    public List<RecruiterCandidateMatchResponse> getMatchedCandidatesForOffer(UUID recruiterId, UUID offerId) {
        return matchRepository.findAllByOfferIdOrderByMatchScoreDesc(offerId).stream()
                .filter(m -> m.getOffer().getRecruiter().getId().equals(recruiterId))
                .map(responseConverter::toRecruiterCandidateMatchResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RecruiterCandidateMatchResponse getCandidateDetailForRecruiter(UUID recruiterId, UUID offerId, UUID candidateId) {
        Match match = matchRepository.findByCandidateIdAndOfferId(candidateId, offerId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));
        if (!match.getOffer().getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");
        return responseConverter.toRecruiterCandidateMatchResponse(match);
    }

    @Transactional
    public void markInterest(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        interactionService.revealProfile(match);
        matchRepository.save(match);
    }

    @Transactional
    public void declineMatch(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        match.setStatus(new MatchStatus.NotInterested());
        matchRepository.save(match);
    }

    private Match findMatch(UUID candidateId, UUID offerId) {
        return matchRepository.findByCandidateIdAndOfferId(candidateId, offerId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));
    }
}
