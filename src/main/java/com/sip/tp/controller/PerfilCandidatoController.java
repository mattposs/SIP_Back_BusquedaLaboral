package com.sip.tp.controller;

import com.sip.tp.entity.PerfilCandidato;
import com.sip.tp.repository.PerfilCandidatoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/perfiles/candidatos")
public class PerfilCandidatoController {
    private final PerfilCandidatoRepository PerfilCandidatoRepository;

    public PerfilCandidatoController(PerfilCandidatoRepository PerfilCandidatoRepository) {
        this.PerfilCandidatoRepository = PerfilCandidatoRepository;
    }

    @PostMapping
    public PerfilCandidato save(@RequestBody PerfilCandidato perfilCandidato) {
        return PerfilCandidatoRepository.save(perfilCandidato);
    }
}
