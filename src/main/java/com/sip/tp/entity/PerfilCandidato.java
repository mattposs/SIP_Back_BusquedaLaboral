package com.sip.tp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class PerfilCandidato {
    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String titulo;
    private List<String> habilidades;
    private List<String> experiencia;
    private List<String> proyectos;
}
