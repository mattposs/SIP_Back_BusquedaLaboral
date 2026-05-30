package com.sip.tp.types.definition;

public sealed interface Modality {
    String code();

    record Remote() implements Modality {
        @Override
        public String code() {
            return "REMOTE";
        }
    }

    record Hybrid() implements Modality {
        @Override
        public String code() {
            return "HYBRID";
        }
    }

    record OnSite() implements Modality {
        @Override
        public String code() {
            return "ONSITE";
        }
    }
}