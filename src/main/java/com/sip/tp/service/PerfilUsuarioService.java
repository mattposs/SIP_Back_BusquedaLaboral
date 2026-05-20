package com.sip.tp.service;

import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService {
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioService(PerfilUsuarioRepository perfilUsuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    public void save(PerfilUsuario perfilUsuario) {
        perfilUsuarioRepository.save(perfilUsuario);
    }
}
