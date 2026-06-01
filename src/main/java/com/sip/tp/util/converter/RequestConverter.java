package com.sip.tp.util.converter;

import com.sip.tp.dto.request.CompanyRequest;
import com.sip.tp.dto.request.JobOfferRequest;
import com.sip.tp.dto.request.ProfileUpdateRequest;
import com.sip.tp.dto.request.ProjectRequest;
import com.sip.tp.dto.request.WorkExperienceRequest;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Company;
import com.sip.tp.entity.JobOffer;
import com.sip.tp.entity.Project;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.entity.WorkExperience;
import com.sip.tp.types.definition.CompanySize;
import com.sip.tp.types.definition.ExperienceRange;
import com.sip.tp.types.definition.Industry;
import com.sip.tp.types.definition.Modality;
import com.sip.tp.types.definition.OfferStatus;
import com.sip.tp.types.definition.RelationType;
import com.sip.tp.types.definition.RequestStatus;
import com.sip.tp.types.definition.Seniority;
import com.sip.tp.types.definition.SkillLevel;
import com.sip.tp.types.definition.ThreadCategory;
import com.sip.tp.types.definition.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class RequestConverter {

    public UserType toUserType(String value) {
        return switch (value.toUpperCase()) {
            case "CANDIDATE" -> new UserType.Candidate();
            case "RECRUITER" -> new UserType.Recruiter();
            default -> throw new IllegalArgumentException("Invalid user type provided");
        };
    }

    public Company toCompany(CompanyRequest request) {
        return Company.builder()
                .name(request.name())
                .website(request.website())
                .industry(toIndustry(request.industry()))
                .size(toCompanySize(request.size()))
                .cultureDescription(request.cultureDescription())
                .isPartner(false)
                .build();
    }

    public Industry toIndustry(String value) {
        return switch (value.toUpperCase()) {
            case "TECH" -> new Industry.Tech();
            case "FINANCE" -> new Industry.Finance();
            case "ECOMMERCE" -> new Industry.Ecommerce();
            case "CONSULTING" -> new Industry.Consulting();
            default -> new Industry.Other();
        };
    }

    public CompanySize toCompanySize(String value) {
        return switch (value) {
            case "1-10" -> new CompanySize.Size1To10();
            case "11-50" -> new CompanySize.Size11To50();
            case "51-200" -> new CompanySize.Size51To200();
            case "201-1000" -> new CompanySize.Size201To1000();
            default -> new CompanySize.Size1000Plus();
        };
    }

    public JobOffer toJobOffer(Recruiter recruiter, JobOfferRequest request) {
        return JobOffer.builder()
                .recruiter(recruiter)
                .company(recruiter.getCompany())
                .title(request.title())
                .modality(toModality(request.modality()))
                .seniority(toSeniority(request.seniority()))
                .description(request.description())
                .location(request.location())
                .benefits(request.benefits())
                .salaryMin(request.salaryMin() != null ? BigDecimal.valueOf(request.salaryMin()) : null)
                .salaryMax(request.salaryMax() != null ? BigDecimal.valueOf(request.salaryMax()) : null)
                .status(new OfferStatus.Draft())
                .build();
    }

    public Modality toModality(String value) {
        return switch (value.toUpperCase()) {
            case "REMOTE" -> new Modality.Remote();
            case "HYBRID" -> new Modality.Hybrid();
            default -> new Modality.OnSite();
        };
    }

    public Seniority toSeniority(String value) {
        return switch (value.toUpperCase()) {
            case "JUNIOR" -> new Seniority.Junior();
            case "SEMI_SENIOR" -> new Seniority.SemiSenior();
            case "SENIOR" -> new Seniority.Senior();
            default -> new Seniority.Lead();
        };
    }

    public ExperienceRange toExperienceRange(String value) {
        return switch (value) {
            case "<1 year" -> new ExperienceRange.LessThan1Year();
            case "1-3 years" -> new ExperienceRange.Years1To3();
            case "4-6 years" -> new ExperienceRange.Years4To6();
            case "7-10 years" -> new ExperienceRange.Years7To10();
            case "10+ years" -> new ExperienceRange.Years10Plus();
            default -> throw new IllegalArgumentException("Invalid experience range");
        };
    }

    public RequestStatus toRequestStatus(String value) {
        return switch (value.toUpperCase()) {
            case "COMPLETED" -> new RequestStatus.Completed();
            case "REJECTED" -> new RequestStatus.Rejected();
            default -> new RequestStatus.Pending();
        };
    }

    public RelationType toRelationType(String value) {
        return switch (value.toUpperCase()) {
            case "COWORKER" -> new RelationType.Coworker();
            case "MANAGER" -> new RelationType.Manager();
            case "TECHLEAD" -> new RelationType.TechLead();
            case "CLASSMATE" -> new RelationType.Classmate();
            case "TEAMMATE" -> new RelationType.Teammate();
            default -> new RelationType.None();
        };
    }

    public SkillLevel toSkillLevel(String value) {
        return switch (value.toUpperCase()) {
            case "COLABORADOR" -> new SkillLevel.Colaborador();
            case "EJECUTOR_AUTONOMO" -> new SkillLevel.EjecutorAutonomo();
            case "LIDER" -> new SkillLevel.Lider();
            case "REFERENTE" -> new SkillLevel.Referente();
            default -> throw new IllegalArgumentException("Invalid skill level");
        };
    }

    public ThreadCategory toThreadCategory(String value) {
        return switch (value.toUpperCase()) {
            case "SALARY" -> new ThreadCategory.Salary();
            case "CULTURE" -> new ThreadCategory.Culture();
            case "STACK" -> new ThreadCategory.Stack();
            case "BENEFITS" -> new ThreadCategory.Benefits();
            case "MODALITY" -> new ThreadCategory.Modality();
            default -> new ThreadCategory.Other();
        };
    }

    public void applyProfileUpdate(Candidate candidate, ProfileUpdateRequest request) {
        candidate.setLocation(request.location());
        candidate.setCurrentRoleTitle(request.currentRole());
        candidate.setHeadline(request.headline());
        candidate.setPhone(request.phone());
        candidate.setLinkedIn(request.linkedIn());
    }

    public WorkExperience toWorkExperience(Candidate candidate, WorkExperienceRequest request) {
        return WorkExperience.builder()
                .candidate(candidate)
                .company(request.company())
                .position(request.position())
                .startDate(LocalDate.parse(request.startDate()))
                .endDate(request.endDate() != null ? LocalDate.parse(request.endDate()) : null)
                .isCurrent(request.isCurrent())
                .description(request.description())
                .build();
    }

    public void applyWorkExperienceUpdate(WorkExperience experience, WorkExperienceRequest request) {
        experience.setCompany(request.company());
        experience.setPosition(request.position());
        experience.setStartDate(LocalDate.parse(request.startDate()));
        experience.setEndDate(request.endDate() != null ? LocalDate.parse(request.endDate()) : null);
        experience.setIsCurrent(request.isCurrent());
        experience.setDescription(request.description());
    }

    public Project toProject(Candidate candidate, ProjectRequest request) {
        return Project.builder()
                .candidate(candidate)
                .title(request.title())
                .description(request.description())
                .link(request.link())
                .build();
    }

    public void applyProjectUpdate(Project project, ProjectRequest request) {
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setLink(request.link());
    }
}

