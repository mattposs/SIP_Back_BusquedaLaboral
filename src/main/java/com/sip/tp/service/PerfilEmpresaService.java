package com.sip.tp.service;

import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.model.PerfilEmpresaData;
import com.sip.tp.repository.PerfilEmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PerfilEmpresaService {
    private final PerfilEmpresaRepository perfilEmpresaRepository;

    public PerfilEmpresa saveProfile(PerfilEmpresaData perfilEmpresaData) {
        return perfilEmpresaRepository.save(new PerfilEmpresa(perfilEmpresaData.nombre(),
                perfilEmpresaData.industria(),
                perfilEmpresaData.numeroTrabajadores()));
    }

    public PerfilEmpresa updateProfile(PerfilEmpresaData perfilEmpresaData) {
        PerfilEmpresa empresa = perfilEmpresaRepository.findPerfilEmpresaByIdEmpresa(perfilEmpresaData.idEmpresa());
        if (empresa != null) {
            empresa.setNombre(perfilEmpresaData.nombre());
            empresa.setIndustria(perfilEmpresaData.industria());
            empresa.setNumeroTrabajadores(perfilEmpresaData.numeroTrabajadores());
            return perfilEmpresaRepository.save(empresa);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public PerfilEmpresa findPerfilEmpresaByNombre(PerfilEmpresaData perfilEmpresaData) {
        return perfilEmpresaRepository.findPerfilEmpresaByIdEmpresa(perfilEmpresaData.idEmpresa());
    }

    public void deleteProfile(PerfilEmpresaData perfilEmpresaData) {
        PerfilEmpresa empresa = findPerfilEmpresaByNombre(perfilEmpresaData);
        if (empresa != null) {
            perfilEmpresaRepository.delete(empresa);
        } else {
            throw new RuntimeException("Perfil de usuario no encontrado");
        }
    }
}
