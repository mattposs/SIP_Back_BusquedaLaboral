package com.sip.tp.service;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.Postulacion;
import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.model.CreacionPostulacionOferta;
import com.sip.tp.repository.OfertaEmpleoRepository;
import com.sip.tp.repository.PerfilUsuarioRepository;
import com.sip.tp.repository.PostulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostulacionService {
    private final OfertaEmpleoRepository ofertaEmpleoRepository;
    private final PostulacionRepository postulacionRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public Postulacion saveApplication(CreacionPostulacionOferta postulacion) {
        OfertaEmpleo ofertaEmpleo = ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(postulacion.idOferta());
        PerfilUsuario perfilUsuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(postulacion.idCandidato());
        return postulacionRepository.save(new Postulacion(perfilUsuario, ofertaEmpleo, postulacion.urlCurriculum()));
    }

    public Postulacion updateApplication(CreacionPostulacionOferta postulacionData) {
        OfertaEmpleo ofertaEmpleo = ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(postulacionData.idOferta());
        PerfilUsuario perfilUsuario = perfilUsuarioRepository.findPerfilUsuarioByIdUsuario(postulacionData.idCandidato());
        Postulacion postulacion = postulacionRepository.findPostulacionByCandidatoAndOfertaEquals(perfilUsuario, ofertaEmpleo);
        if (postulacion != null) {
            postulacion.setEstadoPostulacion(postulacionData.estadoPostulacion());
            return postulacionRepository.save(postulacion);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public List<Postulacion> findApplicationByTitle(CreacionPostulacionOferta postulacionData) {
        return postulacionRepository.findAllByOfertaEquals(ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(postulacionData.idOferta()));
    }

    public void deleteApplication(CreacionPostulacionOferta postulacionData) {
        Postulacion postulacion = postulacionRepository.findAllByOfertaEquals(ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(postulacionData.idOferta())).getFirst();
        if (postulacion != null) {
            postulacionRepository.delete(postulacion);
        } else {
            throw new RuntimeException("Postulación no encontrada");
        }
    }
}
