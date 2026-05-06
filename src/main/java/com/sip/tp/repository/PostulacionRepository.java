package com.sip.tp.repository;

import com.sip.tp.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostulacionRepository extends JpaRepository<Postulacion, UUID> {
}
