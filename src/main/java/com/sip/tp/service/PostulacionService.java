package com.sip.tp.service;

import com.sip.tp.entity.Postulacion;
import com.sip.tp.repository.PostulacionRepository;
import org.springframework.stereotype.Service;

@Service
public class PostulacionService {
    private final PostulacionRepository PostulacionRepository;

    public PostulacionService(PostulacionRepository PostulacionRepository) {
        this.PostulacionRepository = PostulacionRepository;
    }

    public void save(Postulacion postulacion) {
        PostulacionRepository.save(postulacion);
    }
}
