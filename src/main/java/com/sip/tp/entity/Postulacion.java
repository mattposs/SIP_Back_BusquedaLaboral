package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Postulacion {
    @Id
    private UUID id;

    @ManyToOne
    private PerfilCandidato candidato;

    @ManyToOne
    private OfertaEmpleo oferta;

    private String estadoPostulacion;

    private LocalDate fechaPostulacion;

    private String urlCurriculum;

}
