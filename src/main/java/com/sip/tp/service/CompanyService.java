package com.sip.tp.service;

import com.sip.tp.controller.OfferAndMatchController;
import com.sip.tp.entity.Company;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.repository.CompanyRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.types.definition.CompanySize;
import com.sip.tp.types.definition.Industry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;

    @Transactional
    public UUID createCompany(UUID recruiterId, OfferAndMatchController.CompanyRequest request) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId).orElseThrow();

        Industry ind = switch (request.industry().toUpperCase()) {
            case "TECH" -> new Industry.Tech();
            case "FINANCE" -> new Industry.Finance();
            case "ECOMMERCE" -> new Industry.Ecommerce();
            case "CONSULTING" -> new Industry.Consulting();
            default -> new Industry.Other();
        };

        CompanySize size = switch (request.size()) {
            case "1-10" -> new CompanySize.Size1To10();
            case "11-50" -> new CompanySize.Size11To50();
            case "51-200" -> new CompanySize.Size51To200();
            case "201-1000" -> new CompanySize.Size201To1000();
            default -> new CompanySize.Size1000Plus();
        };

        Company company = Company.builder()
                .name(request.name()).website(request.website())
                .industry(ind).size(size).cultureDescription(request.cultureDescription())
                .isPartner(false).build();

        Company saved = companyRepository.save(company);
        recruiter.setCompany(saved);
        recruiterRepository.save(recruiter);
        return saved.getId();
    }

    @Transactional
    public void updateCompany(UUID recruiterId, UUID companyId, OfferAndMatchController.CompanyRequest request) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        // In reality, check if recruiter belongs to this company
        company.setName(request.name());
        company.setCultureDescription(request.cultureDescription());
        companyRepository.save(company);
    }
}