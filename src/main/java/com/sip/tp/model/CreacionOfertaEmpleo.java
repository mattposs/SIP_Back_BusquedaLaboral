package com.sip.tp.model;

import java.util.List;

public record CreacionOfertaEmpleo(
        String nombreEmpresa,
        String nombreReclutador,
        String titulo,
        String modalidad,
        String estadoOferta,
        String descripcion,
        List<String> habilidadesRequeridas
) {
}
