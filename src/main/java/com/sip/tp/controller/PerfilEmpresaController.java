package com.sip.tp.controller;

import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.model.PerfilEmpresaData;
import com.sip.tp.service.PerfilEmpresaService;
import org.springframework.web.bind.annotation.*;

@RestController("v1/perfiles/empresas")
public class PerfilEmpresaController {
    private final PerfilEmpresaService perfilEmpresaService;

    public PerfilEmpresaController(PerfilEmpresaService perfilEmpresaService) {
        this.perfilEmpresaService = perfilEmpresaService;
    }

    @PostMapping
    public PerfilEmpresa save(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.saveProfile(perfilEmpresa);
    }

    @PutMapping
    public PerfilEmpresa update(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.updateProfile(perfilEmpresa);
    }

    @GetMapping
    public PerfilEmpresa get(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.findPerfilEmpresaByNombre(perfilEmpresa);
    }

    @DeleteMapping
    public void delete(@RequestBody PerfilEmpresaData perfilEmpresa) {
        perfilEmpresaService.deleteProfile(perfilEmpresa);
    }
}
