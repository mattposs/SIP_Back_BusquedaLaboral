package com.sip.tp.types.definition;

public sealed interface OfferStatus {
    String code();

    record Draft() implements OfferStatus {
        @Override
        public String code() {
            return "DRAFT";
        }
    }

    record Published() implements OfferStatus {
        @Override
        public String code() {
            return "PUBLISHED";
        }
    }

    record Closed() implements OfferStatus {
        @Override
        public String code() {
            return "CLOSED";
        }
    }
}
