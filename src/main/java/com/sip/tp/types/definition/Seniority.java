package com.sip.tp.types.definition;

public sealed interface Seniority {
    String code();

    record Junior() implements Seniority {
        @Override
        public String code() {
            return "JUNIOR";
        }
    }

    record SemiSenior() implements Seniority {
        @Override
        public String code() {
            return "SEMI_SENIOR";
        }
    }

    record Senior() implements Seniority {
        @Override
        public String code() {
            return "SENIOR";
        }
    }

    record Lead() implements Seniority {
        @Override
        public String code() {
            return "LEAD";
        }
    }
}
