package com.sip.tp.service.domain.recruiter;

import com.sip.tp.dto.request.JobOfferRequest;
import com.sip.tp.dto.request.OfferSkillRequest;
import com.sip.tp.dto.response.JobOfferDetailResponse;
import com.sip.tp.dto.response.JobOfferResponse;
import com.sip.tp.entity.JobOffer;
import com.sip.tp.entity.OfferSkill;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.entity.Skill;
import com.sip.tp.repository.JobOfferRepository;
import com.sip.tp.repository.OfferSkillRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.repository.SkillRepository;
import com.sip.tp.service.domain.matching.MatchGenerationService;
import com.sip.tp.types.definition.OfferStatus;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository offerRepository;
    private final RecruiterRepository recruiterRepository;
    private final OfferSkillRepository offerSkillRepository;
    private final SkillRepository skillRepository;
    private final MatchGenerationService matchGenerationService;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional
    public UUID createOffer(UUID recruiterId, JobOfferRequest request) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId).orElseThrow();
        JobOffer offer = requestConverter.toJobOffer(recruiter, request);
        JobOffer saved = offerRepository.save(offer);
        replaceOfferSkills(saved, request.skills());
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public List<JobOfferResponse> getOffersByRecruiter(UUID recruiterId) {
        return offerRepository.findAllByRecruiterId(recruiterId).stream()
                .map(responseConverter::toJobOfferResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JobOfferDetailResponse getOfferById(UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        return responseConverter.toJobOfferDetailResponse(offer, offerSkillRepository.findAllByOffer_Id(offerId));
    }

    @Transactional
    public void updateOffer(UUID recruiterId, UUID offerId, JobOfferRequest request) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        if (!offer.getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");

        offer.setTitle(request.title());
        offer.setDescription(request.description());
        offer.setLocation(request.location());
        offer.setBenefits(request.benefits());
        if (request.salaryMin() != null) offer.setSalaryMin(BigDecimal.valueOf(request.salaryMin()));
        if (request.salaryMax() != null) offer.setSalaryMax(BigDecimal.valueOf(request.salaryMax()));
        offerRepository.save(offer);
        replaceOfferSkills(offer, request.skills());

        if (offer.getStatus() instanceof OfferStatus.Published) {
            matchGenerationService.generateMatchesForOffer(offerId);
        }
    }

    @Transactional
    public void publishOffer(UUID recruiterId, UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        if (!offer.getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");
        offer.setStatus(new OfferStatus.Published());
        offerRepository.save(offer);
        matchGenerationService.generateMatchesForOffer(offerId);
    }

    private void replaceOfferSkills(JobOffer offer, List<OfferSkillRequest> skills) {
        offerSkillRepository.deleteAllByOffer_Id(offer.getId());
        if (skills == null || skills.isEmpty()) {
            return;
        }

        for (OfferSkillRequest skillRequest : skills) {
            Skill skill = skillRepository.findById(skillRequest.skillId()).orElseThrow();
            OfferSkill offerSkill = requestConverter.toOfferSkill(
                    offer,
                    skill,
                    requestConverter.toRequirement(skillRequest.requirement())
            );
            offerSkillRepository.save(offerSkill);
        }
    }
}
