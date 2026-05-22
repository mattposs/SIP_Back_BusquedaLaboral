package com.sip.tp.model;

public record CreacionRecomendacion(
        int usuarioId,
        int recomendadorId,
        String estado,
        String detalleRecomendacion
) {
}
