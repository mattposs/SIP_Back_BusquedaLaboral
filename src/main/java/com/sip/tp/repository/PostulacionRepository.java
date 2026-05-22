package com.sip.tp.repository;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostulacionRepository extends JpaRepository<Postulacion, UUID> {
    Postulacion findPostulacionByCandidatoAndOfertaEquals(PerfilUsuario candidato, OfertaEmpleo oferta);
    List<Postulacion> findAllByOfertaEquals(OfertaEmpleo oferta);
}
