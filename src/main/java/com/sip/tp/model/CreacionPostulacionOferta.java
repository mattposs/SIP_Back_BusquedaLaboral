package com.sip.tp.model;

import java.time.LocalDateTime;

public record CreacionPostulacionOferta(
        int idCandidato,
        int idOferta,
        String estadoPostulacion,
        LocalDateTime fechaPostulacion,
        String urlCurriculum
) {
}
