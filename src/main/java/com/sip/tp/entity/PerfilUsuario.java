package com.sip.tp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String titulo;
    private List<String> habilidades;
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    private PerfilEmpresa empresaActual;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ExperienciaUsuario> experiencia;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProyectoUsuario> proyectos;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Recomendacion> recomendaciones;

    public PerfilUsuario(String nombre, String apellido, String documento, String email, String titulo, PerfilEmpresa empresaActual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.titulo = titulo;
        this.empresaActual = empresaActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PerfilUsuario that = (PerfilUsuario) o;

        return new EqualsBuilder().append(getId(), that.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("nombre", nombre)
                .append("apellido", apellido)
                .append("documento", documento)
                .append("email", email)
                .append("titulo", titulo)
                .append("habilidades", habilidades)
                .append("empresaActual", empresaActual)
                .append("experiencia", experiencia)
                .append("proyectos", proyectos)
                .append("recomendaciones", recomendaciones)
                .toString();
    }
}
