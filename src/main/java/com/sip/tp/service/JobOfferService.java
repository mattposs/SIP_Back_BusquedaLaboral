package com.sip.tp.service;

import com.sip.tp.dto.request.JobOfferRequest;
import com.sip.tp.dto.response.JobOfferResponse;
import com.sip.tp.dto.response.JobOfferDetailResponse;
import com.sip.tp.entity.JobOffer;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.repository.JobOfferRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.types.definition.Modality;
import com.sip.tp.types.definition.OfferStatus;
import com.sip.tp.types.definition.Seniority;
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

    @Transactional
    public UUID createOffer(UUID recruiterId, JobOfferRequest request) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId).orElseThrow();

        Modality mod = switch (request.modality().toUpperCase()) {
            case "REMOTE" -> new Modality.Remote();
            case "HYBRID" -> new Modality.Hybrid();
            default -> new Modality.OnSite();
        };

        Seniority sen = switch (request.seniority().toUpperCase()) {
            case "JUNIOR" -> new Seniority.Junior();
            case "SEMI_SENIOR" -> new Seniority.SemiSenior();
            case "SENIOR" -> new Seniority.Senior();
            default -> new Seniority.Lead();
        };

        JobOffer offer = JobOffer.builder()
                .recruiter(recruiter).company(recruiter.getCompany())
                .title(request.title()).modality(mod).seniority(sen)
                .description(request.description()).location(request.location())
                .benefits(request.benefits())
                .salaryMin(request.salaryMin() != null ? BigDecimal.valueOf(request.salaryMin()) : null)
                .salaryMax(request.salaryMax() != null ? BigDecimal.valueOf(request.salaryMax()) : null)
                .status(new OfferStatus.Draft())
                .build();

        return offerRepository.save(offer).getId();
    }

    @Transactional(readOnly = true)
    public List<JobOfferResponse> getOffersByRecruiter(UUID recruiterId) {
        return offerRepository.findAllByRecruiterId(recruiterId).stream()
                .map(o -> new JobOfferResponse(o.getId(), o.getTitle(), o.getModality().code(), o.getSeniority().code(),
                        o.getStatus().code(), o.getLocation(), o.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JobOfferDetailResponse getOfferById(UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        return new JobOfferDetailResponse(offer.getId(), offer.getTitle(), offer.getModality().code(),
                offer.getSeniority().code(), offer.getDescription(), offer.getSalaryMin(), offer.getSalaryMax(),
                offer.getBenefits(), offer.getLocation(), offer.getStatus().code(), offer.getCreatedAt());
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
    }

    @Transactional
    public void publishOffer(UUID recruiterId, UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        if (!offer.getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");
        offer.setStatus(new OfferStatus.Published());
        offerRepository.save(offer);
    }
}



