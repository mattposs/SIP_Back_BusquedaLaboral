package com.sip.tp.model;

public record PerfilUsuarioData(
        String nombre,
        String apellido,
        String documento,
        String email,
        String titulo,
        String nombreEmpresaActual
) {
}
