package com.sip.tp.controller;

import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.repository.PerfilEmpresaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/perfiles/empresas")
public class PerfilEmpresaController {
    private final PerfilEmpresaRepository PerfilEmpresaRepository;

    public PerfilEmpresaController(PerfilEmpresaRepository PerfilEmpresaRepository) {
        this.PerfilEmpresaRepository = PerfilEmpresaRepository;
    }

    @PostMapping
    public PerfilEmpresa save(@RequestBody PerfilEmpresa perfilEmpresa) {
        return PerfilEmpresaRepository.save(perfilEmpresa);
    }
}
