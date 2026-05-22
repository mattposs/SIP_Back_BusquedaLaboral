package com.sip.tp.service;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.entity.PerfilEmpresa;
import com.sip.tp.entity.PerfilUsuario;
import com.sip.tp.entity.Recomendacion;
import com.sip.tp.model.OfertaEmpleoData;
import com.sip.tp.repository.OfertaEmpleoRepository;
import com.sip.tp.repository.PerfilEmpresaRepository;
import com.sip.tp.repository.PerfilUsuarioRepository;
import com.sip.tp.repository.RecomendacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecomendacionService {
    private final OfertaEmpleoRepository ofertaEmpleoRepository;
    private final PerfilEmpresaRepository perfilEmpresaRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    //TODO Finish this service
    public OfertaEmpleo saveOffer(OfertaEmpleoData ofertaEmpleoData) {
        PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findPerfilEmpresaByIdEmpresa(ofertaEmpleoData.idEmpresa());
        PerfilUsuario perfilUsuario = perfilUsuarioRepository.findPerfilUsuarioByEmail(ofertaEmpleoData.correoReclutador());
        return ofertaEmpleoRepository.save(new OfertaEmpleo(perfilEmpresa,
                perfilUsuario,
                ofertaEmpleoData.titulo(),
                ofertaEmpleoData.modalidad(),
                ofertaEmpleoData.descripcion(),
                ofertaEmpleoData.habilidadesRequeridas()));
    }

    public OfertaEmpleo updateOffer(OfertaEmpleoData ofertaEmpleoData) {
        OfertaEmpleo ofertaEmpleo = ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(ofertaEmpleoData.idOferta());
        if (ofertaEmpleo != null) {
            ofertaEmpleo.setEstadoOferta(ofertaEmpleoData.estadoOferta());
            ofertaEmpleo.setDescripcion(ofertaEmpleoData.descripcion());
            ofertaEmpleo.setHabilidadesRequeridas(ofertaEmpleoData.habilidadesRequeridas());
            ofertaEmpleo.setModalidad(ofertaEmpleoData.modalidad());
            return ofertaEmpleoRepository.save(ofertaEmpleo);
        }
        throw new RuntimeException("Perfil de usuario no encontrado");
    }

    public OfertaEmpleo findOfferByTitle(OfertaEmpleoData ofertaEmpleoData) {
        return ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(ofertaEmpleoData.idOferta());
    }

    public void deleteOffer(OfertaEmpleoData ofertaEmpleoData) {
        OfertaEmpleo ofertaEmpleo = ofertaEmpleoRepository.findOfertaEmpleoByIdOferta(ofertaEmpleoData.idOferta());
        if (ofertaEmpleo != null) {
            ofertaEmpleoRepository.delete(ofertaEmpleo);
        } else {
            throw new RuntimeException("Oferta de empleo no encontrada");
        }
    }
}
