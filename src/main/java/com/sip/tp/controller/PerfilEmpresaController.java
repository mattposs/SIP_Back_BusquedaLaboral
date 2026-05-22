package com.sip.tp.controller;

import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.model.PerfilEmpresaData;
import com.sip.tp.service.PerfilEmpresaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/perfiles/empresas")
public class PerfilEmpresaController {
    private final PerfilEmpresaService perfilEmpresaService;

    public PerfilEmpresaController(PerfilEmpresaService perfilEmpresaService) {
        this.perfilEmpresaService = perfilEmpresaService;
    }

    @PostMapping("/saveCompanyProfile")
    public PerfilEmpresa saveCompanyProfile(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.saveProfile(perfilEmpresa);
    }

    @PutMapping("/updateCompanyProfile")
    public PerfilEmpresa updateCompanyProfile(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.updateProfile(perfilEmpresa);
    }

    @GetMapping("/getCompanyProfile")
    public PerfilEmpresa getCompanyProfile(@RequestBody PerfilEmpresaData perfilEmpresa) {
        return perfilEmpresaService.findPerfilEmpresaByNombre(perfilEmpresa);
    }

    @DeleteMapping("/deleteCompanyProfile")
    public void deleteCompanyProfile(@RequestBody PerfilEmpresaData perfilEmpresa) {
        perfilEmpresaService.deleteProfile(perfilEmpresa);
    }
}
