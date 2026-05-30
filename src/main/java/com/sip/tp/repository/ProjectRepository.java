package com.sip.tp.repository;

import com.sip.tp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    boolean existsByCandidateId(UUID id);

    List<Project> findAllByCandidateId(UUID requesterId);
}
