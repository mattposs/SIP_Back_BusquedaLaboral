package com.sip.tp.types.definition;

public sealed interface SkillType {
    String code();

    record Tech() implements SkillType {
        @Override
        public String code() {
            return "TECH";
        }
    }

    record Soft() implements SkillType {
        @Override
        public String code() {
            return "SOFT";
        }
    }
}
