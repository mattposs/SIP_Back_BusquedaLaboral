package com.sip.tp.service;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.model.PerfilUsuarioData;
import com.sip.tp.repository.PerfilEmpresaRepository;
import com.sip.tp.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PerfilUsuarioService {
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final PerfilEmpresaRepository perfilEmpresaRepository;

    public PerfilUsuario saveProfile(PerfilUsuarioData perfilUsuarioData) {
        PerfilEmpresa empresa = perfilEmpresaRepository.findPerfilEmpresaByIdEmpresa(perfilUsuarioData.idEmpresaActual());
        return perfilUsuarioRepository.save(new PerfilUsuario(perfilUsuarioData.nombre(),
                perfilUsuarioData.apellido(),
                perfilUsuarioData.documento(),
                perfilUsuarioData.email(),
                perfilUsuarioData.titulo(),
                empresa));
    }

    public PerfilUsuario updateProfile(PerfilUsuarioData perfilUsuarioData) {
        PerfilUsuario perfilUsuario = perfilUsuarioRepository.findPerfilUsuarioByDocumento(perfilUsuarioData.documento());
        PerfilEmpresa empresa = perfilEmpresaRepository.findPerfilEmpresaByIdEmpresa(perfilUsuarioData.idEmpresaActual());
        if (perfilUsuario != null) {
            perfilUsuario.setNombre(perfilUsuarioData.nombre());
            perfilUsuario.setApellido(perfilUsuarioData.apellido());
            perfilUsuario.setEmail(perfilUsuarioData.email());
            perfilUsuario.setTitulo(perfilUsuarioData.titulo());
            perfilUsuario.setEmpresaActual(empresa);
            return perfilUsuarioRepository.save(perfilUsuario);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public PerfilUsuario findPerfilUsuarioByDocumento(PerfilUsuarioData perfilUsuarioData) {
        return perfilUsuarioRepository.findPerfilUsuarioByDocumento(perfilUsuarioData.documento());
    }

    public void deleteProfile(PerfilUsuarioData perfilUsuarioData) {
        PerfilUsuario perfilUsuario = findPerfilUsuarioByDocumento(perfilUsuarioData);
        if (perfilUsuario != null) {
            perfilUsuarioRepository.delete(perfilUsuario);
        } else {
            throw new RuntimeException("Perfil de usuario no encontrado");
        }
    }
}
