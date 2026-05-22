package com.sip.tp.repository;

import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.entity.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, UUID> {
    Recomendacion findRecomendacionByUsuarioAndRecomendadorEquals(PerfilUsuario usuario, PerfilUsuario recomendador);

    List<Recomendacion> findAllByUsuario(PerfilUsuario usuario);
}
