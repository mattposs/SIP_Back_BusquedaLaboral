package com.sip.tp.repository;

import com.sip.tp.entity.PerfilEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilEmpresaRepository extends JpaRepository<PerfilEmpresa, UUID> {
}
