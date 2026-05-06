package com.sip.tp.repository;

import com.sip.tp.entity.OfertaEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfertaEmpleoRepository extends JpaRepository<OfertaEmpleo, UUID> {
}
