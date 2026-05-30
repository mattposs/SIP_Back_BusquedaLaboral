package com.sip.tp.types.definition;

public sealed interface UserType {
    String code();

    record Candidate() implements UserType {
        @Override
        public String code() {
            return "CANDIDATE";
        }
    }

    record Recruiter() implements UserType {
        @Override
        public String code() {
            return "RECRUITER";
        }
    }
}