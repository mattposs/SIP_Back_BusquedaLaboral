package com.sip.tp.types.definition;

public sealed interface ThreadCategory {
    String code();

    record Salary() implements ThreadCategory {
        @Override
        public String code() {
            return "SALARY";
        }
    }

    record Culture() implements ThreadCategory {
        @Override
        public String code() {
            return "CULTURE";
        }
    }

    record Stack() implements ThreadCategory {
        @Override
        public String code() {
            return "STACK";
        }
    }

    record Benefits() implements ThreadCategory {
        @Override
        public String code() {
            return "BENEFITS";
        }
    }

    record Modality() implements ThreadCategory {
        @Override
        public String code() {
            return "MODALITY";
        }
    }

    record Other() implements ThreadCategory {
        @Override
        public String code() {
            return "OTHER";
        }
    }
}