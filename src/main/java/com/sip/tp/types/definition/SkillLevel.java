package com.sip.tp.types.definition;

public sealed interface SkillLevel {
    String code();

    record Colaborador() implements SkillLevel {
        @Override
        public String code() {
            return "COLABORADOR";
        }
    }

    record EjecutorAutonomo() implements SkillLevel {
        @Override
        public String code() {
            return "EJECUTOR_AUTONOMO";
        }
    }

    record Lider() implements SkillLevel {
        @Override
        public String code() {
            return "LIDER";
        }
    }

    record Referente() implements SkillLevel {
        @Override
        public String code() {
            return "REFERENTE";
        }
    }
}
