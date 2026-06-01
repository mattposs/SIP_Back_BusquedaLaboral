package com.sip.tp.repository;

import com.sip.tp.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, UUID> {
    List<Validation> findAllByCandidateIdAndSkillId(UUID candidateId, UUID skillId);

    List<Validation> findAllByValidatorId(UUID validatorId);

    List<Validation> findAllByCandidateId(UUID candidateId);
}
