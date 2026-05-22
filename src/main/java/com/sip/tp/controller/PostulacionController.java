package com.sip.tp.controller;

import com.sip.tp.entity.Postulacion;
import com.sip.tp.model.CreacionPostulacionOferta;
import com.sip.tp.service.PostulacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("v1/postulaciones")
public class PostulacionController {
    private final PostulacionService postulacionService;

    public PostulacionController(PostulacionService postulacionService) {
        this.postulacionService = postulacionService;
    }

    @PostMapping("/saveApplication")
    public Postulacion saveApplication(@RequestBody CreacionPostulacionOferta postulacionData) {
        return postulacionService.saveApplication(postulacionData);
    }

    @PutMapping("/updateApplication")
    public Postulacion updateApplication(@RequestBody CreacionPostulacionOferta postulacionData) {
        return postulacionService.updateApplication(postulacionData);
    }

    @GetMapping("/getApplication")
    public List<Postulacion> getApplication(@RequestBody CreacionPostulacionOferta postulacionData) {
        return postulacionService.findApplicationByTitle(postulacionData);
    }

    @DeleteMapping("/deleteApplication")
    public void deleteApplication(@RequestBody CreacionPostulacionOferta postulacionData) {
        postulacionService.deleteApplication(postulacionData);
    }
}
