package com.sip.tp.controller;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.repository.OfertaEmpleoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/oferta")
public class OfertaEmpleoController {
    private final OfertaEmpleoRepository ofertaEmpleoRepository;

    public OfertaEmpleoController(OfertaEmpleoRepository ofertaEmpleoRepository) {
        this.ofertaEmpleoRepository = ofertaEmpleoRepository;
    }

    @PostMapping
    public OfertaEmpleo save(@RequestBody OfertaEmpleo ofertaEmpleo) {
        return ofertaEmpleoRepository.save(ofertaEmpleo);
    }
}
