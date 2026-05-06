package com.sip.tp.service;

import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.repository.PerfilEmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilEmpresaService {
    private final PerfilEmpresaRepository perfilEmpresaRepository;

    public PerfilEmpresaService(PerfilEmpresaRepository perfilEmpresaRepository) {
        this.perfilEmpresaRepository = perfilEmpresaRepository;
    }

    public void save(PerfilEmpresa perfilEmpresa) {
        perfilEmpresaRepository.save(perfilEmpresa);
    }
}
