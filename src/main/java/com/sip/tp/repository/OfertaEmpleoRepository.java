package com.sip.tp.repository;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfertaEmpleoRepository extends JpaRepository<OfertaEmpleo, UUID> {
    List<OfertaEmpleo> findAllByEmpresa(PerfilEmpresa empresa);

    OfertaEmpleo findOfertaEmpleoByIdOferta(int idOferta);

    List<OfertaEmpleo> findAllByReclutador(PerfilUsuario reclutador);
}
