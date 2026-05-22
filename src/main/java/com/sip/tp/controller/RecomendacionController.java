package com.sip.tp.controller;

import com.sip.tp.entity.Recomendacion;
import com.sip.tp.model.CreacionRecomendacion;
import com.sip.tp.service.RecomendacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/recomendaciones")
public class RecomendacionController {
    private final RecomendacionService recomendacionService;

    public RecomendacionController(RecomendacionService recomendacionService) {
        this.recomendacionService = recomendacionService;
    }

    @PostMapping("/saveRecommendation")
    public Recomendacion saveRecommendation(@RequestBody CreacionRecomendacion recomendacionData) {
        return recomendacionService.saveRecommendation(recomendacionData);
    }

    @PutMapping("/updateRecommendation")
    public Recomendacion updateRecommendation(@RequestBody CreacionRecomendacion recomendacionData) {
        return recomendacionService.updateRecommendation(recomendacionData);
    }

    @GetMapping("/getRecommendation")
    public List<Recomendacion> getRecommendation(@RequestBody CreacionRecomendacion recomendacionData) {
        return recomendacionService.findRecommendationsByUser(recomendacionData);
    }

    @DeleteMapping("/deleteRecommendation")
    public void deleteRecommendation(@RequestBody CreacionRecomendacion recomendacionData) {
        recomendacionService.deleteRecommendation(recomendacionData);
    }
}
