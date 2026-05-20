package com.sip.tp.model;

import java.time.LocalDateTime;

public record CreacionPostulacionOferta(
        String correoCandidato,
        String identificadorOferta,
        String estadoPostulacion,
        LocalDateTime fechaPostulacion,
        String urlCurriculum
) {
}
