package com.sip.tp.dto.match;

/**
 * One entry per candidate skill that has at least one completed validation.
 * Shows the highest-reputation validator's comment/name/company.
 */
public record CandidateSkillDetailResponse(
        String skillName,
        String skillType,
        String consolidatedLevel,
        String validationComment,
        String validatorName,
        String validatorCompany
) {
}

