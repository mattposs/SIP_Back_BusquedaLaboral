package com.sip.tp.repository;

import com.sip.tp.entity.PerfilCandidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilCandidatoRepository extends JpaRepository<PerfilCandidato, UUID> {
}
