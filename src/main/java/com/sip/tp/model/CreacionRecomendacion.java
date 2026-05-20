package com.sip.tp.model;

public record CreacionRecomendacion(
        String correoUsuario,
        String correoRecomendador,
        String estado,
        String textoRecomendacion
) {
}
