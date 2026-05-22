package com.sip.tp.controller;

import com.sip.tp.entity.Recomendacion;
import com.sip.tp.repository.RecomendacionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/recomendaciones")
public class RecomendacionController {
    private final RecomendacionRepository RecomendacionRepository;

    public RecomendacionController(RecomendacionRepository RecomendacionRepository) {
        this.RecomendacionRepository = RecomendacionRepository;
    }

    @PostMapping("/saveRecommendation")
    public Recomendacion saveRecommendation(@RequestBody Recomendacion recomendacion) {
        return RecomendacionRepository.save(recomendacion);
    }
}
