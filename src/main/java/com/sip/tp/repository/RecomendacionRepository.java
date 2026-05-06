package com.sip.tp.repository;

import com.sip.tp.entity.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, UUID> {
}
