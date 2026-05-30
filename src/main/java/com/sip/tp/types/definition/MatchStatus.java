package com.sip.tp.types.definition;

public sealed interface MatchStatus {
    String code();

    record Suggested() implements MatchStatus {
        @Override
        public String code() {
            return "SUGGESTED";
        }
    }

    record Interested() implements MatchStatus {
        @Override
        public String code() {
            return "INTERESTED";
        }
    }

    record NotInterested() implements MatchStatus {
        @Override
        public String code() {
            return "NOT_INTERESTED";
        }
    }
}
