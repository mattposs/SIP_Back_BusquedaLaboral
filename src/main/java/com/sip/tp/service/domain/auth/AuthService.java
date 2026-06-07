package com.sip.tp.service.domain.auth;

import com.sip.tp.dto.request.LoginRequest;
import com.sip.tp.dto.request.RegisterRequest;
import com.sip.tp.dto.response.AuthResponse;
import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Company;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.entity.UserData;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.CompanyRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.repository.UserRepository;
import com.sip.tp.types.definition.UserType;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        UserType parsedUserType = requestConverter.toUserType(request.userType());

        switch (parsedUserType) {
            case UserType.Candidate c -> {
                Candidate candidate = new Candidate();
                candidate.setEmail(request.email());
                candidate.setPassword(encodedPassword);
                candidate.setUserType(c);
                candidate.setFullName(request.fullName());
                candidate.setIdentityVerified(false);
                candidate.setProfileCompletion(0);
                candidate.setLocation(request.location() != null ? request.location() : "Not Specified");
                candidate.setCurrentRoleTitle(request.currentRole() != null ? request.currentRole() : "Not Specified");
                candidateRepository.save(candidate);
            }
            case UserType.Recruiter r -> {
                Company company = Company.builder()
                        .name(request.companyName() != null ? request.companyName() : "My Company")
                        .website(request.website())
                        .isPartner(false)
                        .build();
                companyRepository.save(company);

                Recruiter recruiter = new Recruiter();
                recruiter.setEmail(request.email());
                recruiter.setPassword(encodedPassword);
                recruiter.setUserType(r);
                recruiter.setCompany(company);
                recruiterRepository.save(recruiter);
            }
        }
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserData userData = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("User not found after successful authentication"));

        String jwtToken = jwtService.generateToken(userData);
        return responseConverter.toAuthResponse(jwtToken, userData);
    }
}
