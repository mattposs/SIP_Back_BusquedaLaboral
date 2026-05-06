package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class OfertaEmpleo {
    @Id
    private UUID id;

    @OneToOne
    private PerfilEmpresa empresa;

    @ManyToOne
    private PerfilReclutador reclutador;

    private String titulo;
    private String modalidad;
    private String estadoOferta;

    private String descripcion;

    private List<String> habilidadesRequeridas;
}
