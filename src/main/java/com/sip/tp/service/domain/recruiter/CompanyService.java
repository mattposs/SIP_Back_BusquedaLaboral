package com.sip.tp.service.domain.recruiter;

import com.sip.tp.dto.request.CompanyRequest;
import com.sip.tp.dto.response.CompanyResponse;
import com.sip.tp.entity.Company;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.repository.CompanyRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional
    public UUID createCompany(UUID recruiterId, CompanyRequest request) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId).orElseThrow();

        Company company = requestConverter.toCompany(request);
        Company saved = companyRepository.save(company);

        recruiter.setCompany(saved);
        recruiterRepository.save(recruiter);
        return saved.getId();
    }

    @Transactional
    public void updateCompany(UUID recruiterId, UUID companyId, CompanyRequest request) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        company.setName(request.name());
        company.setCultureDescription(request.cultureDescription());
        companyRepository.save(company);
    }

    @Transactional(readOnly = true)
    public CompanyResponse getCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        return responseConverter.toCompanyResponse(company);
    }
}
