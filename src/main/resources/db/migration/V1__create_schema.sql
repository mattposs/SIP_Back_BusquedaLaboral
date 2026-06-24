-- ============================================================
-- V1 — DDL: Create all tables in foreign-key dependency order
-- ============================================================

-- =========================
-- 1. Independent tables
-- =========================

CREATE TABLE skill (
    id   UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE company (
    id                  UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    name                VARCHAR(255) NOT NULL,
    logo                VARCHAR(255),
    website             VARCHAR(255),
    industry            VARCHAR(255),
    size                VARCHAR(255),
    culture_description TEXT,
    is_partner          BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- =========================
-- 2. User hierarchy (JOINED inheritance)
-- =========================

CREATE TABLE user_data (
    id         UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    user_type  VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE candidate (
    id                 UUID         PRIMARY KEY REFERENCES user_data(id),
    full_name          VARCHAR(255) NOT NULL,
    location           VARCHAR(255) NOT NULL,
    current_role_title VARCHAR(255) NOT NULL,
    headline           VARCHAR(255),
    profile_photo      VARCHAR(255),
    phone              VARCHAR(255),
    linked_in          VARCHAR(255),
    identity_verified  BOOLEAN      NOT NULL DEFAULT FALSE,
    profile_completion INTEGER      NOT NULL DEFAULT 0
);

CREATE TABLE recruiter (
    id         UUID PRIMARY KEY REFERENCES user_data(id),
    company_id UUID NOT NULL    REFERENCES company(id)
);

-- =========================
-- 3. Candidate-dependent tables
-- =========================

CREATE TABLE work_experience (
    id           UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id UUID         NOT NULL REFERENCES candidate(id),
    company      VARCHAR(255) NOT NULL,
    position     VARCHAR(255) NOT NULL,
    start_date   DATE         NOT NULL,
    end_date     DATE,
    is_current   BOOLEAN      NOT NULL,
    description  TEXT
);

CREATE TABLE candidate_skill (
    id                 UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id       UUID         NOT NULL REFERENCES candidate(id),
    skill_id           UUID         NOT NULL REFERENCES skill(id),
    experience_range   VARCHAR(255) NOT NULL,
    consolidated_level VARCHAR(255),
    consolidated_score NUMERIC(4, 2),
    created_at         TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE project (
    id           UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id UUID         NOT NULL REFERENCES candidate(id),
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    link         VARCHAR(255)
);

CREATE TABLE validator_reputation (
    user_id            UUID           PRIMARY KEY REFERENCES candidate(id),
    reputation_level   VARCHAR(255)   NOT NULL,
    reputation_score   NUMERIC(4, 2)  NOT NULL,
    platform_years     INTEGER        NOT NULL,
    total_validations  INTEGER        NOT NULL,
    success_rate       INTEGER        NOT NULL,
    seniority          VARCHAR(255)   NOT NULL,
    identity_verified  BOOLEAN        NOT NULL
);

-- =========================
-- 4. Job offers & related
-- =========================

CREATE TABLE job_offer (
    id           UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    recruiter_id UUID         NOT NULL REFERENCES recruiter(id),
    company_id   UUID         NOT NULL REFERENCES company(id),
    title        VARCHAR(255) NOT NULL,
    modality     VARCHAR(255) NOT NULL,
    seniority    VARCHAR(255) NOT NULL,
    description  TEXT,
    salary_min   NUMERIC(38, 2),
    salary_max   NUMERIC(38, 2),
    benefits     TEXT,
    location     VARCHAR(255),
    status       VARCHAR(255) NOT NULL,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE offer_skill (
    offer_id    UUID         NOT NULL REFERENCES job_offer(id),
    skill_id    UUID         NOT NULL REFERENCES skill(id),
    requirement VARCHAR(255) NOT NULL,
    PRIMARY KEY (offer_id, skill_id)
);

-- =========================
-- 5. Matching
-- =========================

CREATE TABLE match (
    id               UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id     UUID         NOT NULL REFERENCES candidate(id),
    offer_id         UUID         NOT NULL REFERENCES job_offer(id),
    match_score      INTEGER      NOT NULL,
    status           VARCHAR(255) NOT NULL,
    profile_revealed BOOLEAN      NOT NULL DEFAULT FALSE,
    revealed_at      TIMESTAMPTZ,
    created_at       TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- =========================
-- 6. Anonymous messaging
-- =========================

CREATE TABLE anonymous_thread (
    id             UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    anonymous_code VARCHAR(255) NOT NULL UNIQUE,
    candidate_id   UUID         NOT NULL REFERENCES candidate(id),
    offer_id       UUID         NOT NULL REFERENCES job_offer(id),
    category       VARCHAR(255) NOT NULL,
    status         VARCHAR(255) NOT NULL,
    created_at     TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE anonymous_message (
    id          UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    thread_id   UUID         NOT NULL REFERENCES anonymous_thread(id),
    sender_type VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- =========================
-- 7. Peer-validation system
-- =========================

CREATE TABLE validation_request (
    id            UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    requester_id  UUID         NOT NULL REFERENCES candidate(id),
    validator_id  UUID         NOT NULL REFERENCES candidate(id),
    skill_id      UUID         NOT NULL REFERENCES skill(id),
    relation_type VARCHAR(255) NOT NULL,
    message       TEXT,
    status        VARCHAR(255) NOT NULL,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE validation (
    id                    UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    validation_request_id UUID         NOT NULL UNIQUE REFERENCES validation_request(id),
    validator_id          UUID         NOT NULL REFERENCES candidate(id),
    candidate_id          UUID         NOT NULL REFERENCES candidate(id),
    skill_id              UUID         NOT NULL REFERENCES skill(id),
    assigned_level        VARCHAR(255) NOT NULL,
    comment               TEXT,
    created_at            TIMESTAMPTZ  NOT NULL DEFAULT now()
);

