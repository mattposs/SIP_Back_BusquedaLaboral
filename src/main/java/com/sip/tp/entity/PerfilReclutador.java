package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class PerfilReclutador {
    @Id
    private UUID id;
    @OneToOne
    private PerfilEmpresa empresaId;
    private String nombre;
    private String cargo;
}
