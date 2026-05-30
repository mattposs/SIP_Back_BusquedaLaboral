package com.sip.tp.types.definition;

public sealed interface ExperienceRange {
    String code();

    record LessThan1Year() implements ExperienceRange {
        @Override
        public String code() {
            return "<1 year";
        }
    }

    record Years1To3() implements ExperienceRange {
        @Override
        public String code() {
            return "1-3 years";
        }
    }

    record Years4To6() implements ExperienceRange {
        @Override
        public String code() {
            return "4-6 years";
        }
    }

    record Years7To10() implements ExperienceRange {
        @Override
        public String code() {
            return "7-10 years";
        }
    }

    record Years10Plus() implements ExperienceRange {
        @Override
        public String code() {
            return "10+ years";
        }
    }
}
