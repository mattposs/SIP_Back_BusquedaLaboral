package com.sip.tp.service.domain.matching;

import com.sip.tp.dto.match.*;
import com.sip.tp.dto.response.WorkExperienceResponse;
import com.sip.tp.entity.*;
import com.sip.tp.repository.*;
import com.sip.tp.service.domain.messaging.AnonymousInteractionService;
import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchFlowService {

    private final MatchRepository matchRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final ValidationRepository validationRepository;
    private final ValidatorReputationRepository validatorReputationRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final MatchGenerationService matchGenerationService;
    private final AnonymousInteractionService interactionService;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public List<CandidateMatchResponse> getCandidateMatches(UUID candidateId) {
        return matchRepository.findAllByCandidateIdOrderByMatchScoreDesc(candidateId).stream()
                .map(responseConverter::toCandidateMatchResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MatchDetailResponse getMatchDetail(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        return responseConverter.toMatchDetailResponse(
                match,
                matchGenerationService.getMatchingSkills(candidateId, offerId),
                matchGenerationService.getMissingSkills(candidateId, offerId)
        );
    }

    @Transactional(readOnly = true)
    public List<RecruiterCandidateMatchResponse> getAllRecruiterCandidates(UUID recruiterId) {
        return matchRepository.findAllByOfferRecruiterIdOrderByMatchScoreDesc(recruiterId).stream()
                .map(m -> responseConverter.toRecruiterCandidateMatchResponse(m, getSkillNames(m.getCandidate().getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RecruiterCandidateMatchResponse> getMatchedCandidatesForOffer(UUID recruiterId, UUID offerId) {
        return matchRepository.findAllByOfferIdOrderByMatchScoreDesc(offerId).stream()
                .filter(m -> m.getOffer().getRecruiter().getId().equals(recruiterId))
                .map(m -> responseConverter.toRecruiterCandidateMatchResponse(m, getSkillNames(m.getCandidate().getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public RecruiterCandidateDetailResponse getCandidateDetailForRecruiter(UUID recruiterId, UUID offerId, UUID candidateId) {
        Match match = matchRepository.findByCandidateIdAndOfferId(candidateId, offerId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));
        if (!match.getOffer().getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");

        Candidate candidate = match.getCandidate();
        List<String> skillNames = getSkillNames(candidateId);

        if (!match.getProfileRevealed()) {
            // Limited profile: only matchId, candidateId, offerId, matchScore, identityVerified, skills
            return new RecruiterCandidateDetailResponse(
                    match.getId(),
                    candidate.getId(),
                    offerId,
                    match.getMatchScore(),
                    false,
                    null,
                    null,
                    null,
                    candidate.getIdentityVerified(),
                    skillNames,
                    null,
                    null,
                    null,
                    List.of(),
                    List.of(),
                    List.of()
            );
        }

        // Full profile: populate all sub-collections
        List<CandidateSkillDetailResponse> candidateSkillDetails = buildCandidateSkillDetails(candidateId);
        List<CandidateValidatorDetailResponse> validatorDetails = buildValidatorDetails(candidateId);
        List<WorkExperienceResponse> workExperienceResponses = workExperienceRepository.findAllByCandidateId(candidateId).stream()
                .map(responseConverter::toWorkExperienceResponse)
                .toList();

        return new RecruiterCandidateDetailResponse(
                match.getId(),
                candidate.getId(),
                offerId,
                match.getMatchScore(),
                true,
                candidate.getFullName(),
                candidate.getCurrentRoleTitle(),
                candidate.getLocation(),
                candidate.getIdentityVerified(),
                skillNames,
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getLinkedIn(),
                candidateSkillDetails,
                validatorDetails,
                workExperienceResponses
        );
    }

    /**
     * Builds CandidateSkillDetailResponse list per §3.1:
     * For each CandidateSkill with a consolidatedLevel, find the highest-reputation validator
     * and use their comment, name, and current company.
     */
    private List<CandidateSkillDetailResponse> buildCandidateSkillDetails(UUID candidateId) {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findAllByCandidateId(candidateId);

        return candidateSkills.stream()
                .filter(cs -> cs.getConsolidatedLevel() != null)
                .map(cs -> {
                    List<Validation> validations = validationRepository.findAllByCandidateIdAndSkillId(candidateId, cs.getSkill().getId());

                    // Find highest-reputation validator for this skill
                    Validation topValidation = findHighestReputationValidation(validations);

                    String validationComment = null;
                    String validatorName = null;
                    String validatorCompany = null;

                    if (topValidation != null) {
                        validationComment = topValidation.getComment();
                        validatorName = topValidation.getValidator().getFullName();
                        validatorCompany = resolveValidatorCompany(topValidation.getValidator().getId());
                    }

                    return new CandidateSkillDetailResponse(
                            cs.getSkill().getName(),
                            cs.getSkill().getType().code(),
                            cs.getConsolidatedLevel().code(),
                            validationComment,
                            validatorName,
                            validatorCompany
                    );
                })
                .toList();
    }

    /**
     * Builds CandidateValidatorDetailResponse list per §3.1:
     * One entry per (validatorId, skillId) pair — one per validation.
     */
    private List<CandidateValidatorDetailResponse> buildValidatorDetails(UUID candidateId) {
        List<Validation> allValidations = validationRepository.findAllByCandidateId(candidateId);

        return allValidations.stream()
                .map(validation -> {
                    Candidate validator = validation.getValidator();
                    ValidatorReputation reputation = validatorReputationRepository.findById(validator.getId()).orElse(null);

                    String company = resolveValidatorCompany(validator.getId());
                    boolean companyPartner = resolveCompanyPartner(validator.getId());

                    return new CandidateValidatorDetailResponse(
                            validator.getFullName(),
                            validator.getCurrentRoleTitle(),
                            company,
                            reputation != null ? reputation.getReputationLevel().code() : null,
                            reputation != null ? reputation.getPlatformYears() : 0,
                            reputation != null ? reputation.getTotalValidations() : 0,
                            reputation != null ? reputation.getSuccessRate() : 0,
                            reputation != null ? reputation.getSeniority() : null,
                            companyPartner,
                            reputation != null ? reputation.getIdentityVerified() : false,
                            validation.getSkill().getName(),
                            validation.getAssignedLevel().code()
                    );
                })
                .toList();
    }

    /**
     * Finds the validation with the highest-reputation validator from a list.
     */
    private Validation findHighestReputationValidation(List<Validation> validations) {
        if (validations.isEmpty()) return null;

        Validation best = null;
        java.math.BigDecimal bestScore = java.math.BigDecimal.ZERO;

        for (Validation v : validations) {
            Optional<ValidatorReputation> repOpt = validatorReputationRepository.findById(v.getValidator().getId());
            if (repOpt.isPresent()) {
                java.math.BigDecimal score = repOpt.get().getReputationScore();
                if (score.compareTo(bestScore) > 0) {
                    bestScore = score;
                    best = v;
                }
            } else if (best == null) {
                best = v;
            }
        }
        return best;
    }

    /**
     * Resolves the validator's current company from their most recent WorkExperience where isCurrent = true.
     */
    private String resolveValidatorCompany(UUID validatorId) {
        return workExperienceRepository.findAllByCandidateId(validatorId).stream()
                .filter(we -> Boolean.TRUE.equals(we.getIsCurrent()))
                .map(WorkExperience::getCompany)
                .findFirst()
                .orElse(null);
    }

    /**
     * Resolves whether the validator's company is a verified partner.
     * Since Company is linked via Recruiter, and validators are Candidates,
     * we use their current WorkExperience company name — partner status cannot be directly resolved
     * unless the validator is also a recruiter. Returns false by default.
     */
    private boolean resolveCompanyPartner(UUID validatorId) {
        // Validators are candidates; partner status is on Company entity linked to Recruiters.
        // Per spec, this should check if the validator's company is a partner.
        // Since candidates don't have a direct Company FK, we return false as default.
        return false;
    }

    private List<String> getSkillNames(UUID candidateId) {
        return candidateSkillRepository.findAllByCandidateId(candidateId).stream()
                .map(cs -> cs.getSkill().getName())
                .toList();
    }

    @Transactional
    public void markInterest(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        interactionService.revealProfile(match);
        matchRepository.save(match);
    }

    @Transactional
    public void declineMatch(UUID candidateId, UUID offerId) {
        Match match = findMatch(candidateId, offerId);
        match.setStatus(new MatchStatus.NotInterested());
        matchRepository.save(match);
    }

    private Match findMatch(UUID candidateId, UUID offerId) {
        return matchRepository.findByCandidateIdAndOfferId(candidateId, offerId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));
    }
}
