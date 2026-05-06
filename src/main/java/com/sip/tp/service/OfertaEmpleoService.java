package com.sip.tp.service;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.repository.OfertaEmpleoRepository;
import org.springframework.stereotype.Service;

@Service
public class OfertaEmpleoService {
    private final OfertaEmpleoRepository ofertaEmpleoRepository;

    public OfertaEmpleoService(OfertaEmpleoRepository ofertaEmpleoRepository) {
        this.ofertaEmpleoRepository = ofertaEmpleoRepository;
    }

    public void save(OfertaEmpleo ofertaEmpleo) {
        ofertaEmpleoRepository.save(ofertaEmpleo);
    }
}
