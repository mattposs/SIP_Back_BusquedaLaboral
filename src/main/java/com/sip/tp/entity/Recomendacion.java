package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Recomendacion {
    @Id
    private UUID id;
    @ManyToOne
    private PerfilCandidato candidatoId;
    private String nombreRecomendador;
    private String cargoRecomendador;
    private String recomendacion;
}
