package com.sip.tp.util.converter;

import com.sip.tp.dto.match.CandidateMatchResponse;
import com.sip.tp.dto.match.MatchDetailResponse;
import com.sip.tp.dto.match.RecruiterCandidateMatchResponse;
import com.sip.tp.dto.message.AnonymousThreadDetailResponse;
import com.sip.tp.dto.message.AnonymousThreadResponse;
import com.sip.tp.dto.response.*;
import com.sip.tp.dto.skill.CandidateSkillResponse;
import com.sip.tp.dto.skill.SkillResponse;
import com.sip.tp.dto.validation.ValidationRequestResponse;
import com.sip.tp.dto.validation.ValidationResponse;
import com.sip.tp.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseConverter {

    public AuthResponse toAuthResponse(String token, UserData userData) {
        return new AuthResponse(token, userData.getUserType().code());
    }

    public CandidateProfileResponse toCandidateProfileResponse(Candidate candidate) {
        return new CandidateProfileResponse(
                candidate.getId(),
                candidate.getFullName(),
                candidate.getLocation(),
                candidate.getCurrentRoleTitle(),
                candidate.getHeadline(),
                candidate.getProfilePhoto(),
                candidate.getIdentityVerified(),
                candidate.getProfileCompletion()
        );
    }

    public CompletionResponse toCompletionResponse(int percentage) {
        return new CompletionResponse(percentage);
    }

    public CompanyResponse toCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getLogo(),
                company.getWebsite(),
                company.getIndustry() != null ? company.getIndustry().code() : null,
                company.getSize() != null ? company.getSize().code() : null,
                company.getCultureDescription(),
                company.getIsPartner()
        );
    }

    public JobOfferResponse toJobOfferResponse(JobOffer offer) {
        return new JobOfferResponse(
                offer.getId(),
                offer.getTitle(),
                offer.getModality().code(),
                offer.getSeniority().code(),
                offer.getStatus().code(),
                offer.getLocation(),
                offer.getCreatedAt()
        );
    }

    public JobOfferDetailResponse toJobOfferDetailResponse(JobOffer offer) {
        return new JobOfferDetailResponse(
                offer.getId(),
                offer.getTitle(),
                offer.getModality().code(),
                offer.getSeniority().code(),
                offer.getDescription(),
                offer.getSalaryMin(),
                offer.getSalaryMax(),
                offer.getBenefits(),
                offer.getLocation(),
                offer.getStatus().code(),
                offer.getCreatedAt()
        );
    }

    public SkillResponse toSkillResponse(Skill skill) {
        return new SkillResponse(skill.getId(), skill.getName(), skill.getType().code());
    }

    public CandidateSkillResponse toCandidateSkillResponse(CandidateSkill candidateSkill) {
        return new CandidateSkillResponse(
                candidateSkill.getId(),
                candidateSkill.getSkill().getName(),
                candidateSkill.getExperienceRange().code(),
                candidateSkill.getConsolidatedLevel() != null ? candidateSkill.getConsolidatedLevel().code() : null,
                candidateSkill.getConsolidatedScore() != null ? candidateSkill.getConsolidatedScore().doubleValue() : null
        );
    }

    public ValidationResponse toValidationResponse(Validation validation) {
        return new ValidationResponse(
                validation.getId(),
                validation.getSkill().getName(),
                validation.getCandidate().getFullName(),
                validation.getValidator().getFullName(),
                validation.getAssignedLevel().code(),
                validation.getComment(),
                validation.getCreatedAt()
        );
    }

    public ValidationRequestResponse toIncomingValidationRequestResponse(ValidationRequest request) {
        return new ValidationRequestResponse(
                request.getId(),
                request.getSkill().getName(),
                request.getRequester().getFullName(),
                request.getRelationType().code(),
                request.getMessage(),
                request.getStatus().code()
        );
    }

    public ValidationRequestResponse toSentValidationRequestResponse(ValidationRequest request) {
        return new ValidationRequestResponse(
                request.getId(),
                request.getSkill().getName(),
                request.getValidator().getFullName(),
                request.getRelationType().code(),
                request.getMessage(),
                request.getStatus().code()
        );
    }

    public CandidateMatchResponse toCandidateMatchResponse(Match match) {
        return new CandidateMatchResponse(
                match.getOffer().getId(),
                match.getOffer().getTitle(),
                match.getOffer().getCompany().getName(),
                match.getMatchScore(),
                match.getStatus().code()
        );
    }

    public MatchDetailResponse toMatchDetailResponse(Match match) {
        return new MatchDetailResponse(
                match.getOffer().getId(),
                match.getOffer().getTitle(),
                match.getOffer().getCompany().getName(),
                match.getMatchScore(),
                match.getStatus().code(),
                match.getProfileRevealed()
        );
    }

    public RecruiterCandidateMatchResponse toRecruiterCandidateMatchResponse(Match match) {
        if (match.getProfileRevealed()) {
            return new RecruiterCandidateMatchResponse(
                    match.getId(),
                    match.getMatchScore(),
                    true,
                    match.getCandidate().getFullName(),
                    match.getCandidate().getEmail(),
                    match.getCandidate().getLinkedIn()
            );
        }

        return new RecruiterCandidateMatchResponse(match.getId(), match.getMatchScore(), false, "Hidden Candidate", null, null);
    }

    public AnonymousThreadResponse toAnonymousThreadResponse(AnonymousThread thread) {
        return new AnonymousThreadResponse(
                thread.getId(),
                thread.getAnonymousCode(),
                thread.getCategory().code(),
                thread.getStatus().code(),
                thread.getCreatedAt()
        );
    }

    public AnonymousThreadDetailResponse toAnonymousThreadDetailResponse(AnonymousThread thread, List<AnonymousMessage> messages) {
        List<AnonymousThreadDetailResponse.AnonymousMessageResponse> mappedMessages = messages.stream()
                .map(this::toAnonymousMessageResponse)
                .collect(Collectors.toList());

        return new AnonymousThreadDetailResponse(
                thread.getId(),
                thread.getAnonymousCode(),
                thread.getCategory().code(),
                thread.getStatus().code(),
                mappedMessages,
                thread.getCreatedAt()
        );
    }

    public AnonymousThreadDetailResponse.AnonymousMessageResponse toAnonymousMessageResponse(AnonymousMessage message) {
        return new AnonymousThreadDetailResponse.AnonymousMessageResponse(
                message.getId(),
                message.getSenderType().code(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}

