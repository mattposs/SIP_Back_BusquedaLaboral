package com.sip.tp.types.definition;

public sealed interface ReputationLevel {
    String code();

    record Bronce() implements ReputationLevel {
        @Override
        public String code() {
            return "BRONCE";
        }
    }

    record Plata() implements ReputationLevel {
        @Override
        public String code() {
            return "PLATA";
        }
    }

    record Oro() implements ReputationLevel {
        @Override
        public String code() {
            return "ORO";
        }
    }

    record Platino() implements ReputationLevel {
        @Override
        public String code() {
            return "PLATINO";
        }
    }
}
