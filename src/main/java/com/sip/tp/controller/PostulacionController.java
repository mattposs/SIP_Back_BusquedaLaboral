package com.sip.tp.controller;

import com.sip.tp.entity.Postulacion;
import com.sip.tp.repository.PostulacionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/postulaciones")
public class PostulacionController {
    private final PostulacionRepository PostulacionRepository;

    public PostulacionController(PostulacionRepository PostulacionRepository) {
        this.PostulacionRepository = PostulacionRepository;
    }

    @PostMapping("/saveApplication")
    public Postulacion saveApplication(@RequestBody Postulacion postulacion) {
        return PostulacionRepository.save(postulacion);
    }
}
