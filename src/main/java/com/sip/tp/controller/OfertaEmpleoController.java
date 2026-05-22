package com.sip.tp.controller;

import com.sip.tp.entity.OfertaEmpleo;
import com.sip.tp.model.OfertaEmpleoData;
import com.sip.tp.service.OfertaEmpleoService;
import org.springframework.web.bind.annotation.*;

@RestController("v1/oferta")
public class OfertaEmpleoController {
    private final OfertaEmpleoService ofertaEmpleoService;

    public OfertaEmpleoController(OfertaEmpleoService perfilUsuarioService) {
        this.ofertaEmpleoService = perfilUsuarioService;
    }

    @PostMapping("/saveOffer")
    public OfertaEmpleo saveOffer(@RequestBody OfertaEmpleoData perfilUsuario) {
        return ofertaEmpleoService.saveOffer(perfilUsuario);
    }

    @PutMapping("/updateOffer")
    public OfertaEmpleo updateOffer(@RequestBody OfertaEmpleoData perfilUsuario) {
        return ofertaEmpleoService.updateOffer(perfilUsuario);
    }

    @GetMapping("/getOffer")
    public OfertaEmpleo getOffer(@RequestBody OfertaEmpleoData perfilUsuario) {
        return ofertaEmpleoService.findOfferByTitle(perfilUsuario);
    }

    @DeleteMapping("/deleteOffer")
    public void deleteOffer(@RequestBody OfertaEmpleoData perfilUsuario) {
        ofertaEmpleoService.deleteOffer(perfilUsuario);
    }
}
