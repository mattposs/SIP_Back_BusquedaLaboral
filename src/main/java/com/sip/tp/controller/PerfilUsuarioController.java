package com.sip.tp.controller;

import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.model.PerfilUsuarioData;
import com.sip.tp.service.PerfilUsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController("v1/perfiles/candidatos")
public class PerfilUsuarioController {
    private final PerfilUsuarioService perfilUsuarioService;

    public PerfilUsuarioController(PerfilUsuarioService perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @PostMapping
    public PerfilUsuario save(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.saveProfile(perfilUsuario);
    }

    @PutMapping
    public PerfilUsuario update(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.updateProfile(perfilUsuario);
    }

    @GetMapping
    public PerfilUsuario get(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.findPerfilUsuarioByDocumento(perfilUsuario);
    }

    @DeleteMapping
    public void delete(@RequestBody PerfilUsuarioData perfilUsuario) {
        perfilUsuarioService.deleteProfile(perfilUsuario);
    }
}
