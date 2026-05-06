package com.sip.tp.service;

import com.sip.tp.entity.PerfilCandidato;
import com.sip.tp.repository.PerfilCandidatoRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilCandidatoService {
    private final PerfilCandidatoRepository perfilCandidatoRepository;

    public PerfilCandidatoService(PerfilCandidatoRepository perfilCandidatoRepository) {
        this.perfilCandidatoRepository = perfilCandidatoRepository;
    }

    public void save(PerfilCandidato perfilCandidato) {
        perfilCandidatoRepository.save(perfilCandidato);
    }
}
