package com.sip.tp.dto.match;

/**
 * One entry per unique (validatorId, skillId) pair — i.e., one entry per validation.
 */
public record CandidateValidatorDetailResponse(
        String name,
        String role,
        String company,
        String reputationLevel,
        Integer platformYears,
        Integer totalValidations,
        Integer successRate,
        String seniority,
        Boolean companyPartner,
        Boolean identityVerified,
        String validatedSkill,
        String validatedLevel
) {
}

