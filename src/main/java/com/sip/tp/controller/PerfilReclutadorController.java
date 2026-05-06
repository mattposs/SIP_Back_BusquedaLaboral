package com.sip.tp.controller;

import com.sip.tp.entity.PerfilReclutador;
import com.sip.tp.repository.PerfilReclutadorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/perfiles/reclutadores")
public class PerfilReclutadorController {
    private final PerfilReclutadorRepository PerfilReclutadorRepository;

    public PerfilReclutadorController(PerfilReclutadorRepository PerfilReclutadorRepository) {
        this.PerfilReclutadorRepository = PerfilReclutadorRepository;
    }

    @PostMapping
    public PerfilReclutador save(@RequestBody PerfilReclutador perfilReclutador) {
        return PerfilReclutadorRepository.save(perfilReclutador);
    }
}
