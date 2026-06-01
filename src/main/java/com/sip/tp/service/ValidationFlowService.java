package com.sip.tp.service;

import com.sip.tp.dto.request.SubmitValidationRequest;
import com.sip.tp.dto.request.ValidationRequestPayload;
import com.sip.tp.dto.validation.ValidationRequestResponse;
import com.sip.tp.dto.validation.ValidationResponse;
import com.sip.tp.entity.*;
import com.sip.tp.repository.*;
import com.sip.tp.types.definition.RelationType;
import com.sip.tp.types.definition.RequestStatus;
import com.sip.tp.types.definition.SkillLevel;
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

    @Transactional(readOnly = true)
    public List<ValidationResponse> getGivenValidations(UUID validatorId) {
        return validationRepository.findAllByValidatorId(validatorId).stream()
                .map(v -> new ValidationResponse(v.getId(), v.getSkill().getName(), v.getCandidate().getFullName(), v.getValidator().getFullName(), v.getAssignedLevel().code(), v.getComment(), v.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationResponse> getReceivedValidations(UUID candidateId) {
        return validationRepository.findAllByCandidateId(candidateId).stream()
                .map(v -> new ValidationResponse(v.getId(), v.getSkill().getName(), v.getCandidate().getFullName(), v.getValidator().getFullName(), v.getAssignedLevel().code(), v.getComment(), v.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationRequestResponse> getIncomingRequests(UUID validatorId, String status) {
        RequestStatus reqStatus = parseRequestStatus(status);
        return requestRepository.findAllByValidatorIdAndStatus(validatorId, reqStatus).stream()
                .map(r -> new ValidationRequestResponse(r.getId(), r.getSkill().getName(), r.getRequester().getFullName(), r.getRelationType().code(), r.getMessage(), r.getStatus().code()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidationRequestResponse> getSentRequests(UUID requesterId, String status) {
        List<ValidationRequest> requests = (status == null || status.isBlank())
                ? requestRepository.findAllByRequesterId(requesterId)
                : requestRepository.findAllByRequesterIdAndStatus(requesterId, parseRequestStatus(status));
        return requests.stream()
                .map(r -> new ValidationRequestResponse(r.getId(), r.getSkill().getName(), r.getValidator().getFullName(), r.getRelationType().code(), r.getMessage(), r.getStatus().code()))
                .collect(Collectors.toList());
    }

    private RequestStatus parseRequestStatus(String status) {
        return switch (status.toUpperCase()) {
            case "COMPLETED" -> new RequestStatus.Completed();
            case "REJECTED" -> new RequestStatus.Rejected();
            default -> new RequestStatus.Pending();
        };
    }

    @Transactional
    public void createValidationRequest(UUID requesterId, ValidationRequestPayload payload) {
        Candidate requester = candidateRepository.getReferenceById(requesterId);
        Candidate validator = candidateRepository.getReferenceById(payload.validatorId());
        Skill skill = skillRepository.getReferenceById(payload.skillId());

        RelationType relation = switch (payload.relationType().toUpperCase()) {
            case "COWORKER" -> new RelationType.Coworker();
            case "MANAGER" -> new RelationType.Manager();
            case "TECHLEAD" -> new RelationType.TechLead();
            case "CLASSMATE" -> new RelationType.Classmate();
            case "TEAMMATE" -> new RelationType.Teammate();
            default -> new RelationType.None();
        };

        ValidationRequest request = ValidationRequest.builder()
                .requester(requester).validator(validator).skill(skill)
                .relationType(relation).message(payload.message())
                .status(new RequestStatus.Pending()).build();

        requestRepository.save(request);
    }

    @Transactional
    public void submitValidation(UUID validatorId, SubmitValidationRequest payload) {
        ValidationRequest request = requestRepository.findById(payload.requestId()).orElseThrow();
        if (!request.getValidator().getId().equals(validatorId)) throw new SecurityException("Unauthorized");

        SkillLevel level = switch (payload.assignedLevel().toUpperCase()) {
            case "COLABORADOR" -> new SkillLevel.Colaborador();
            case "EJECUTOR_AUTONOMO" -> new SkillLevel.EjecutorAutonomo();
            case "LIDER" -> new SkillLevel.Lider();
            case "REFERENTE" -> new SkillLevel.Referente();
            default -> throw new IllegalArgumentException("Invalid skill level");
        };

        Validation validation = Validation.builder()
                .validationRequest(request).validator(request.getValidator())
                .candidate(request.getRequester()).skill(request.getSkill())
                .assignedLevel(level).comment(payload.comment()).build();

        validationRepository.save(validation);
        request.setStatus(new RequestStatus.Completed());

        List<CandidateSkill> skills = candidateSkillRepository.findAllByCandidateId(request.getRequester().getId());
        CandidateSkill skillToUpdate = skills.stream().filter(cs -> cs.getSkill().getId().equals(request.getSkill().getId())).findFirst().orElseThrow();
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