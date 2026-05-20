package com.sip.tp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Postulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private PerfilUsuario candidato;
    @ManyToOne
    private OfertaEmpleo oferta;
    private String estadoPostulacion;
    private LocalDateTime fechaPostulacion;
    private String urlCurriculum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Postulacion that = (Postulacion) o;

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
                .append("candidato", candidato)
                .append("oferta", oferta)
                .append("estadoPostulacion", estadoPostulacion)
                .append("fechaPostulacion", fechaPostulacion)
                .append("urlCurriculum", urlCurriculum)
                .toString();
    }
}
