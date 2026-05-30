package com.sip.tp.types.definition;

public sealed interface Requirement {
    String code();

    record Required() implements Requirement {
        @Override
        public String code() {
            return "REQUIRED";
        }
    }

    record Desirable() implements Requirement {
        @Override
        public String code() {
            return "DESIRABLE";
        }
    }
}
