package com.sip.tp.types.definition;

public sealed interface SenderType {
    String code();

    record Candidate() implements SenderType {
        @Override
        public String code() {
            return "CANDIDATE";
        }
    }

    record Recruiter() implements SenderType {
        @Override
        public String code() {
            return "RECRUITER";
        }
    }
}
