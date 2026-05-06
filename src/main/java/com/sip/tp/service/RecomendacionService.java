package com.sip.tp.service;

import com.sip.tp.entity.Recomendacion;
import com.sip.tp.repository.RecomendacionRepository;
import org.springframework.stereotype.Service;

@Service
public class RecomendacionService {
    private final RecomendacionRepository RecomendacionRepository;

    public RecomendacionService(RecomendacionRepository RecomendacionRepository) {
        this.RecomendacionRepository = RecomendacionRepository;
    }

    public void save(Recomendacion recomendacion) {
        RecomendacionRepository.save(recomendacion);
    }
}
