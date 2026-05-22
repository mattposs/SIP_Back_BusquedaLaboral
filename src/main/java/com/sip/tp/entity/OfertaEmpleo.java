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
public class OfertaEmpleo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private PerfilEmpresa empresa;
    @ManyToOne
    private PerfilUsuario reclutador;
    private String titulo;
    private String modalidad;
    private String estadoOferta;
    private String descripcion;
    private List<String> habilidadesRequeridas;

    public OfertaEmpleo(PerfilEmpresa empresa, PerfilUsuario reclutador, String titulo, String modalidad, String descripcion, List<String> habilidadesRequeridas) {
        this.empresa = empresa;
        this.reclutador = reclutador;
        this.titulo = titulo;
        this.modalidad = modalidad;
        this.estadoOferta = "Disponible";
        this.descripcion = descripcion;
        this.habilidadesRequeridas = habilidadesRequeridas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OfertaEmpleo that = (OfertaEmpleo) o;

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
                .append("empresa", empresa)
                .append("reclutador", reclutador)
                .append("titulo", titulo)
                .append("modalidad", modalidad)
                .append("estadoOferta", estadoOferta)
                .append("descripcion", descripcion)
                .append("habilidadesRequeridas", habilidadesRequeridas)
                .toString();
    }
}
