package com.sip.tp.service;

import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.entity.Recomendacion;
import com.sip.tp.model.CreacionRecomendacion;
import com.sip.tp.repository.PerfilUsuarioRepository;
import com.sip.tp.repository.RecomendacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecomendacionService {
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final RecomendacionRepository recomendacionRepository;

    public Recomendacion saveRecommendation(CreacionRecomendacion creacionRecomendacion) {
        PerfilUsuario usuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.usuarioId());
        PerfilUsuario recomendador = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.recomendadorId());
        return recomendacionRepository.save(new Recomendacion(usuario, recomendador, creacionRecomendacion.detalleRecomendacion()));
    }

    public Recomendacion updateRecommendation(CreacionRecomendacion creacionRecomendacion) {
        PerfilUsuario usuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.usuarioId());
        PerfilUsuario recomendador = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.recomendadorId());
        Recomendacion recomendacion = recomendacionRepository.findRecomendacionByUsuarioAndRecomendadorEquals(usuario, recomendador);
        if (recomendacion != null) {
            recomendacion.setDetalleRecomendacion(creacionRecomendacion.detalleRecomendacion());
            return recomendacionRepository.save(recomendacion);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public List<Recomendacion> findRecommendationsByUser(CreacionRecomendacion creacionRecomendacion) {
        PerfilUsuario usuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.usuarioId());
        return recomendacionRepository.findAllByUsuario(usuario);
    }

    public void deleteRecommendation(CreacionRecomendacion creacionRecomendacion) {
        PerfilUsuario usuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.usuarioId());
        PerfilUsuario recomendador = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(creacionRecomendacion.recomendadorId());
        Recomendacion recomendacion = recomendacionRepository.findRecomendacionByUsuarioAndRecomendadorEquals(usuario, recomendador);
        if (recomendacion != null) {
            recomendacionRepository.delete(recomendacion);
        } else {
            throw new RuntimeException("Oferta de empleo no encontrada");
        }
    }
}
