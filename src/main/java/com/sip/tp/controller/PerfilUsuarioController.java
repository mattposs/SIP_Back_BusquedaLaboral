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

    @PostMapping("/saveUserProfile")
    public PerfilUsuario saveUserProfile(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.saveProfile(perfilUsuario);
    }

    @PutMapping("/updateUserProfile")
    public PerfilUsuario updateUserProfile(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.updateProfile(perfilUsuario);
    }

    @GetMapping("/getUserProfile")
    public PerfilUsuario getUserProfile(@RequestBody PerfilUsuarioData perfilUsuario) {
        return perfilUsuarioService.findPerfilUsuarioByDocumento(perfilUsuario);
    }

    @DeleteMapping("/deleteUserProfile")
    public void deleteUserProfile(@RequestBody PerfilUsuarioData perfilUsuario) {
        perfilUsuarioService.deleteProfile(perfilUsuario);
    }
}
