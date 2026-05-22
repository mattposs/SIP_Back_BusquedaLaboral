package com.sip.tp.repository;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, UUID> {
    PerfilUsuario findPerfilUsuarioByDocumento(String documento);
    PerfilUsuario findPerfilUsuarioByEmail(String email);
}
