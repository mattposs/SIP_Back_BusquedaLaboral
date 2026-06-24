package com.sip.tp.dto.match;

import com.sip.tp.dto.response.WorkExperienceResponse;

import java.util.List;
import java.util.UUID;

/**
 * Enriched candidate detail for recruiters (§3.1).
 * Returns full or limited profile depending on profileRevealed.
 */
public record RecruiterCandidateDetailResponse(
        UUID matchId,
        UUID candidateId,
        UUID offerId,
        Integer matchScore,
        Boolean profileRevealed,
        String candidateName,
        String currentRole,
        String location,
        Boolean identityVerified,
        List<String> skills,
        String email,
        String phone,
        String linkedIn,
        List<CandidateSkillDetailResponse> candidateSkills,
        List<CandidateValidatorDetailResponse> validators,
        List<WorkExperienceResponse> workExperience
) {
}

