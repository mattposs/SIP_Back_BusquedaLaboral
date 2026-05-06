package com.sip.tp.repository;

import com.sip.tp.entity.PerfilReclutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilReclutadorRepository extends JpaRepository<PerfilReclutador, UUID> {
}
