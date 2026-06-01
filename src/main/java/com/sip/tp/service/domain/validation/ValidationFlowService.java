package com.sip.tp.service.domain.validation;

import com.sip.tp.dto.request.SubmitValidationRequest;
import com.sip.tp.dto.request.ValidationRequestPayload;
import com.sip.tp.dto.validation.ValidationRequestResponse;
import com.sip.tp.dto.validation.ValidationResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.CandidateSkill;
import com.sip.tp.entity.Skill;
import com.sip.tp.entity.Validation;
import com.sip.tp.entity.ValidationRequest;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.CandidateSkillRepository;
import com.sip.tp.repository.SkillRepository;
import com.sip.tp.repository.ValidationRepository;
import com.sip.tp.repository.ValidationRequestRepository;
import com.sip.tp.types.definition.RequestStatus;
import com.sip.tp.types.definition.SkillLevel;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationFlowService {

    private final ValidationRequestRepository requestRepository;
    private final ValidationRepository validationRepository;
    private final CandidateRepository candidateRepository;
    private final SkillRepository skillRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final ValidationScoringService scoringService;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional(readOnly = true)
    public List<ValidationResponse> getGivenValidations(UUID validatorId) {
        return validationRepository.findAllByValidatorId(validatorId).stream()
                .map(responseConverter::toValidationResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationResponse> getReceivedValidations(UUID candidateId) {
        return validationRepository.findAllByCandidateId(candidateId).stream()
                .map(responseConverter::toValidationResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationRequestResponse> getIncomingRequests(UUID validatorId, String status) {
        RequestStatus reqStatus = requestConverter.toRequestStatus(status);
        return requestRepository.findAllByValidatorIdAndStatus(validatorId, reqStatus).stream()
                .map(responseConverter::toIncomingValidationRequestResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationRequestResponse> getSentRequests(UUID requesterId, String status) {
        List<ValidationRequest> requests = (status == null || status.isBlank())
                ? requestRepository.findAllByRequesterId(requesterId)
                : requestRepository.findAllByRequesterIdAndStatus(requesterId, requestConverter.toRequestStatus(status));

        return requests.stream()
                .map(responseConverter::toSentValidationRequestResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createValidationRequest(UUID requesterId, ValidationRequestPayload payload) {
        Candidate requester = candidateRepository.getReferenceById(requesterId);
        Candidate validator = candidateRepository.getReferenceById(payload.validatorId());
        Skill skill = skillRepository.getReferenceById(payload.skillId());

        ValidationRequest request = ValidationRequest.builder()
                .requester(requester)
                .validator(validator)
                .skill(skill)
                .relationType(requestConverter.toRelationType(payload.relationType()))
                .message(payload.message())
                .status(new RequestStatus.Pending())
                .build();

        requestRepository.save(request);
    }

    @Transactional
    public void submitValidation(UUID validatorId, SubmitValidationRequest payload) {
        ValidationRequest request = requestRepository.findById(payload.requestId()).orElseThrow();
        if (!request.getValidator().getId().equals(validatorId)) throw new SecurityException("Unauthorized");

        SkillLevel level = requestConverter.toSkillLevel(payload.assignedLevel());

        Validation validation = Validation.builder()
                .validationRequest(request)
                .validator(request.getValidator())
                .candidate(request.getRequester())
                .skill(request.getSkill())
                .assignedLevel(level)
                .comment(payload.comment())
                .build();

        validationRepository.save(validation);
        request.setStatus(new RequestStatus.Completed());

        List<CandidateSkill> skills = candidateSkillRepository.findAllByCandidateId(request.getRequester().getId());
        CandidateSkill skillToUpdate = skills.stream()
                .filter(cs -> cs.getSkill().getId().equals(request.getSkill().getId()))
                .findFirst()
                .orElseThrow();

        List<Validation> allValidations = validationRepository.findAllByCandidateIdAndSkillId(request.getRequester().getId(), request.getSkill().getId());

        scoringService.updateConsolidatedScore(skillToUpdate, allValidations);
        candidateSkillRepository.save(skillToUpdate);
    }

    @Transactional
    public void rejectValidationRequest(UUID validatorId, UUID requestId) {
        ValidationRequest request = requestRepository.findById(requestId).orElseThrow();
        if (!request.getValidator().getId().equals(validatorId)) throw new SecurityException("Unauthorized");
        request.setStatus(new RequestStatus.Rejected());
        requestRepository.save(request);
    }
}
