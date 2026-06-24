package com.sip.tp.repository;

import com.sip.tp.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findAllByCandidateIdOrderByMatchScoreDesc(UUID candidateId);

    List<Match> findAllByOfferIdOrderByMatchScoreDesc(UUID offerId);

    java.util.Optional<Match> findByCandidateIdAndOfferId(UUID candidateId, UUID offerId);

    List<Match> findAllByOfferRecruiterIdOrderByMatchScoreDesc(UUID recruiterId);
}
