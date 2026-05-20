package com.sip.tp.controller;

import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.repository.PerfilUsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/perfiles/candidatos")
public class PerfilUsuarioController {
    private final PerfilUsuarioRepository PerfilUsuarioRepository;

    public PerfilUsuarioController(PerfilUsuarioRepository PerfilUsuarioRepository) {
        this.PerfilUsuarioRepository = PerfilUsuarioRepository;
    }

    @PostMapping
    public PerfilUsuario save(@RequestBody PerfilUsuario perfilUsuario) {
        return PerfilUsuarioRepository.save(perfilUsuario);
    }
}
