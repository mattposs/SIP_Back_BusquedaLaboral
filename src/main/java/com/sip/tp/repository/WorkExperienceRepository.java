package com.sip.tp.repository;

import com.sip.tp.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, UUID> {
    boolean existsByCandidateId(UUID id);

    List<WorkExperience> findAllByCandidateId(UUID requesterId);
}
