package com.sip.tp.types.definition;

public sealed interface RelationType {
    String code();

    record None() implements RelationType {
        @Override
        public String code() {
            return "NONE";
        }
    }

    record Classmate() implements RelationType {
        @Override
        public String code() {
            return "CLASSMATE";
        }
    }

    record Coworker() implements RelationType {
        @Override
        public String code() {
            return "COWORKER";
        }
    }

    record Teammate() implements RelationType {
        @Override
        public String code() {
            return "TEAMMATE";
        }
    }

    record TechLead() implements RelationType {
        @Override
        public String code() {
            return "TECHLEAD";
        }
    }

    record Manager() implements RelationType {
        @Override
        public String code() {
            return "MANAGER";
        }
    }
}