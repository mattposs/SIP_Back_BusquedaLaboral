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

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PerfilEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpresa;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PerfilUsuario> reclutadores;
    private String nombre;
    private String industria;
    private String numeroTrabajadores;

    public PerfilEmpresa(String nombre, String industria, String numeroTrabajadores) {
        this.nombre = nombre;
        this.industria = industria;
        this.numeroTrabajadores = numeroTrabajadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PerfilEmpresa that = (PerfilEmpresa) o;

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
                .append("reclutadores", reclutadores)
                .append("nombre", nombre)
                .append("industria", industria)
                .append("numeroTrabajadores", numeroTrabajadores)
                .toString();
    }
}
