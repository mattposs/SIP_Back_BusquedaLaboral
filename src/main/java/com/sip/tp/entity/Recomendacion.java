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

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private PerfilUsuario usuario;
    @OneToOne
    private PerfilUsuario recomendador;
    private String estado;
    private String detalleRecomendacion;

    public Recomendacion(PerfilUsuario usuario, PerfilUsuario recomendador, String detalleRecomendacion) {
        this.usuario = usuario;
        this.recomendador = recomendador;
        this.estado = "Pendiente";
        this.detalleRecomendacion = detalleRecomendacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Recomendacion that = (Recomendacion) o;

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
                .append("usuario", usuario)
                .append("recomendador", recomendador)
                .toString();
    }
}
