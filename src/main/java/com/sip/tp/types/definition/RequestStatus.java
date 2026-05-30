package com.sip.tp.types.definition;

public sealed interface RequestStatus {
    String code();

    record Pending() implements RequestStatus {
        @Override
        public String code() {
            return "PENDING";
        }
    }

    record Completed() implements RequestStatus {
        @Override
        public String code() {
            return "COMPLETED";
        }
    }

    record Rejected() implements RequestStatus {
        @Override
        public String code() {
            return "REJECTED";
        }
    }
}