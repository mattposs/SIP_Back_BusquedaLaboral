package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class PerfilEmpresa {
    @Id
    private UUID id;
    private String nombre;
    private String industria;
    private String numeroTrabajadores;
}
