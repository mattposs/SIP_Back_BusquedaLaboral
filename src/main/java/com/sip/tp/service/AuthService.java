package com.sip.tp.service;

import com.sip.tp.entity.Candidate;
import com.sip.tp.entity.Recruiter;
import com.sip.tp.entity.UserData;
import com.sip.tp.model.AuthResponse;
import com.sip.tp.model.LoginRequest;
import com.sip.tp.model.RegisterRequest;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.RecruiterRepository;
import com.sip.tp.repository.UserRepository;
import com.sip.tp.types.definition.UserType;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        // Map the string request to our modern Java 25 Sealed Interface
        UserType parsedUserType = switch (request.userType().toUpperCase()) {
            case "CANDIDATE" -> new UserType.Candidate();
            case "RECRUITER" -> new UserType.Recruiter();
            default -> throw new IllegalArgumentException("Invalid user type provided");
        };

        // Pattern matching to handle role-specific registration logic
        switch (parsedUserType) {
            case UserType.Candidate c -> {
                Candidate candidate = new Candidate();
                candidate.setEmail(request.email());
                candidate.setPassword(encodedPassword);
                candidate.setUserType(c);
                candidate.setFullName(request.fullName());
                // Spec defaults:
                candidate.setIdentityVerified(false);
                candidate.setProfileCompletion(0);
                // "location" and "currentRole" are required by spec, normally requested during onboarding step 1
                candidate.setLocation("Not Specified");
                candidate.setCurrentRoleTitle("Not Specified");

                candidateRepository.save(candidate);
            }
            case UserType.Recruiter r -> {
                Recruiter recruiter = new Recruiter();
                recruiter.setEmail(request.email());
                recruiter.setPassword(encodedPassword);
                recruiter.setUserType(r);

                // Note: The backend spec requires a companyId for Recruiter. 
                // Typically, recruiter registration involves a two-step flow or selecting a company from a dropdown.
                // For now, this assumes Company assignment happens immediately after email verification.

                recruiterRepository.save(recruiter);
            }
        }
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        // Authenticate via Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // If we reach here, the credentials are valid
        UserData userData = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("User not found after successful authentication"));

        // Generate the JWT 
        String jwtToken = jwtService.generateToken(userData);

        return new AuthResponse(jwtToken, userData.getUserType().code());
    }
}