package com.sip.tp.model;

import java.util.List;

public record OfertaEmpleoData(
        String nombreEmpresa,
        String nombreReclutador,
        String correoReclutador,
        String titulo,
        String modalidad,
        String estadoOferta,
        String descripcion,
        List<String> habilidadesRequeridas,
        int idOferta,
        int idEmpresa
) {
}
