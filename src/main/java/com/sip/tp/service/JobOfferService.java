package com.sip.tp.service;

import com.sip.tp.controller.OfferAndMatchController;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobOfferService {
    private final JobOfferRepository offerRepository;
    private final RecruiterRepository recruiterRepository;

    @Transactional
    public UUID createOffer(UUID recruiterId, OfferAndMatchController.JobOfferRequest request) {
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
                .salaryMin(BigDecimal.valueOf(request.salaryMin()))
                .salaryMax(BigDecimal.valueOf(request.salaryMax()))
                .status(new OfferStatus.Draft())
                .build();

        return offerRepository.save(offer).getId();
    }

    @Transactional
    public void publishOffer(UUID recruiterId, UUID offerId) {
        JobOffer offer = offerRepository.findById(offerId).orElseThrow();
        if (!offer.getRecruiter().getId().equals(recruiterId)) throw new SecurityException("Unauthorized");
        offer.setStatus(new OfferStatus.Published());
        offerRepository.save(offer);
    }
}