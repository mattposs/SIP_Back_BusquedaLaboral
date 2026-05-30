package com.sip.tp.types.definition;

public sealed interface ThreadStatus {
    String code();

    record Pending() implements ThreadStatus {
        @Override
        public String code() {
            return "PENDING";
        }
    }

    record Responded() implements ThreadStatus {
        @Override
        public String code() {
            return "RESPONDED";
        }
    }
}
