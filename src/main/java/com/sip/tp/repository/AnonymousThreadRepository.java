package com.sip.tp.repository;

import com.sip.tp.entity.AnonymousThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnonymousThreadRepository extends JpaRepository<AnonymousThread, UUID> {
    List<AnonymousThread> findAllByCandidateId(UUID candidateId);
    List<AnonymousThread> findAllByOfferId(UUID offerId);
}
