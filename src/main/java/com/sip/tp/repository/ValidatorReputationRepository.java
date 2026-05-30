package com.sip.tp.repository;

import com.sip.tp.entity.ValidatorReputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ValidatorReputationRepository extends JpaRepository<ValidatorReputation, UUID> {
}
