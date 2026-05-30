package com.sip.tp.service;

import com.sip.tp.entity.Match;
import com.sip.tp.repository.MatchRepository;
import com.sip.tp.types.definition.MatchStatus;
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

    @Transactional(readOnly = true)
    public List<CandidateMatchDto> getCandidateMatches(UUID candidateId) {
        return matchRepository.findAllByCandidateIdOrderByMatchScoreDesc(candidateId).stream()
                .map(m -> new CandidateMatchDto(m.getOffer().getId(), m.getOffer().getTitle(), m.getOffer().getCompany().getName(), m.getMatchScore(), m.getStatus().code()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecruiterCandidateMatchDto> getMatchedCandidatesForOffer(UUID recruiterId, UUID offerId) {
        return matchRepository.findAll().stream()
                .filter(m -> m.getOffer().getId().equals(offerId))
                .filter(m -> m.getOffer().getRecruiter().getId().equals(recruiterId))
                .map(m -> {
                    if (m.getProfileRevealed()) {
                        return new RecruiterCandidateMatchDto(m.getId(), m.getMatchScore(), true,
                                m.getCandidate().getFullName(), m.getCandidate().getEmail(), m.getCandidate().getLinkedIn());
                    } else {
                        // Point 2.6: Hidden identity for non-interested candidates
                        return new RecruiterCandidateMatchDto(m.getId(), m.getMatchScore(), false,
                                "Hidden Candidate", null, null);
                    }
                })
                .collect(Collectors.toList());
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
        return matchRepository.findAllByCandidateIdOrderByMatchScoreDesc(candidateId).stream()
                .filter(m -> m.getOffer().getId().equals(offerId)).findFirst().orElseThrow();
    }

    public record CandidateMatchDto(UUID offerId, String offerTitle, String companyName, Integer matchScore,
                                    String status) {
    }

    // Dynamic output for recruiters based on reveal status
    public record RecruiterCandidateMatchDto(UUID matchId, Integer matchScore, Boolean profileRevealed,
                                             String fullName, String email, String linkedIn) {
    }
}