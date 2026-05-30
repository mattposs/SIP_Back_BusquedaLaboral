package com.sip.tp.types.definition;

public sealed interface Industry {
    String code();

    record Tech() implements Industry {
        @Override
        public String code() {
            return "TECH";
        }
    }

    record Finance() implements Industry {
        @Override
        public String code() {
            return "FINANCE";
        }
    }

    record Ecommerce() implements Industry {
        @Override
        public String code() {
            return "ECOMMERCE";
        }
    }

    record Consulting() implements Industry {
        @Override
        public String code() {
            return "CONSULTING";
        }
    }

    record Other() implements Industry {
        @Override
        public String code() {
            return "OTHER";
        }
    }
}



