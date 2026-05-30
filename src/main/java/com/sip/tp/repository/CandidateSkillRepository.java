package com.sip.tp.repository;

import com.sip.tp.entity.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, UUID> {
    long countByCandidateId(UUID id);

    List<CandidateSkill> findAllByCandidateId(UUID candidateId);

    @Query("SELECT COUNT(cs) FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId AND cs.consolidatedLevel IS NOT NULL")
    long countValidatedByCandidateId(UUID candidateId);
}
