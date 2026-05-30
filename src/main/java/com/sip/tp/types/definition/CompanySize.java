package com.sip.tp.types.definition;

public sealed interface CompanySize {
    String code();

    record Size1To10() implements CompanySize {
        @Override
        public String code() {
            return "1-10";
        }
    }

    record Size11To50() implements CompanySize {
        @Override
        public String code() {
            return "11-50";
        }
    }

    record Size51To200() implements CompanySize {
        @Override
        public String code() {
            return "51-200";
        }
    }

    record Size201To1000() implements CompanySize {
        @Override
        public String code() {
            return "201-1000";
        }
    }

    record Size1000Plus() implements CompanySize {
        @Override
        public String code() {
            return "1000+";
        }
    }
}