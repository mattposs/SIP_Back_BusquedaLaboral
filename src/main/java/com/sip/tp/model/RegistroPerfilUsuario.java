package com.sip.tp.model;

public record RegistroPerfilUsuario(
        String nombre,
        String apellido,
        String documento,
        String email,
        String titulo
) {
}
