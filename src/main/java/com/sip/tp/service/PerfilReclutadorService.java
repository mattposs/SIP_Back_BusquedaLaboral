package com.sip.tp.service;

import com.sip.tp.entity.PerfilReclutador;
import com.sip.tp.repository.PerfilReclutadorRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilReclutadorService {
    private final PerfilReclutadorRepository perfilReclutadorRepository;

    public PerfilReclutadorService(PerfilReclutadorRepository perfilReclutadorRepository) {
        this.perfilReclutadorRepository = perfilReclutadorRepository;
    }

    public void save(PerfilReclutador perfilReclutador) {
        perfilReclutadorRepository.save(perfilReclutador);
    }
}
