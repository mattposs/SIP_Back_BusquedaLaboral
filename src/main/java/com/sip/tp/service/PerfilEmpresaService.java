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

    public PerfilEmpresa saveProfile(PerfilEmpresaData PerfilEmpresaData) {
        return perfilEmpresaRepository.save(new PerfilEmpresa(PerfilEmpresaData.nombre(),
                PerfilEmpresaData.industria(),
                PerfilEmpresaData.numeroTrabajadores()));
    }

    public PerfilEmpresa updateProfile(PerfilEmpresaData perfilEmpresaData) {
        PerfilEmpresa empresa = perfilEmpresaRepository.findPerfilEmpresaByNombreEquals(perfilEmpresaData.nombre());
        if (empresa != null) {
            empresa.setNombre(perfilEmpresaData.nombre());
            empresa.setIndustria(perfilEmpresaData.industria());
            empresa.setNumeroTrabajadores(perfilEmpresaData.numeroTrabajadores());
            return perfilEmpresaRepository.save(empresa);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public PerfilEmpresa findPerfilEmpresaByNombre(PerfilEmpresaData PerfilEmpresaData) {
        return perfilEmpresaRepository.findPerfilEmpresaByNombreEquals(PerfilEmpresaData.nombre());
    }

    public void deleteProfile(PerfilEmpresaData PerfilEmpresaData) {
        PerfilEmpresa empresa = findPerfilEmpresaByNombre(PerfilEmpresaData);
        if (empresa != null) {
            perfilEmpresaRepository.delete(empresa);
        } else {
            throw new RuntimeException("Perfil de usuario no encontrado");
        }
    }
}
