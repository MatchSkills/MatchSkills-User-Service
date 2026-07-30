package com.matchskills.user.service.services;

import com.matchskills.user.service.dtos.auth.CandidateAuthResponse;
import com.matchskills.user.service.dtos.auth.CandidateLoginRequest;
import com.matchskills.user.service.dtos.auth.CompanyAuthResponse;
import com.matchskills.user.service.dtos.auth.CompanyLoginRequest;
import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import com.matchskills.user.service.dtos.candidate.CreateCandidateRequest;
import com.matchskills.user.service.dtos.company.CompanyResponse;
import com.matchskills.user.service.dtos.company.CreateCompanyRequest;
import com.matchskills.user.service.enums.RoleType;
import com.matchskills.user.service.exceptions.customs.candidate.CandidateNotFoundException;
import com.matchskills.user.service.exceptions.customs.company.CompanyNotFoundException;
import com.matchskills.user.service.jwt.JwtService;
import com.matchskills.user.service.repositorys.CandidateRepository;
import com.matchskills.user.service.repositorys.CompanyRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    final private CandidateService candidateService;
    final private CandidateRepository candidateRepository;
    final private CompanyService companyService;
    final private CompanyRepository companyRepository;
    final private JwtService jwtService;
    final private PasswordEncoder passwordEncoder;

    public  AuthService(CandidateService candidateService,
                        CandidateRepository candidateRepository,
                        CompanyService companyService,
                        CompanyRepository companyRepository,
                        JwtService jwtService,
                        PasswordEncoder passwordEncoder
    ) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.jwtService = jwtService;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateAuthResponse registerCandidate(CreateCandidateRequest createCandidateRequest) {

        var newCandidate = candidateService.createCandidate(createCandidateRequest);

        var tokens = jwtService.createTokens(newCandidate.getId(), RoleType.Candidate.name());

        return new CandidateAuthResponse(tokens, newCandidate);

    }

    public CompanyAuthResponse registerCompany(CreateCompanyRequest createCompanyRequest) {

        var newCompany = companyService.createCompany(createCompanyRequest);

        var tokens = jwtService.createTokens(newCompany.getId(), RoleType.Company.name());

        return new CompanyAuthResponse(tokens, newCompany);

    }

    public CandidateAuthResponse loginCandidate(CandidateLoginRequest candidateLoginRequest) {

        var targetCandidate = candidateRepository.findByEmail(candidateLoginRequest.getEmail())
                .orElseThrow(CandidateNotFoundException::new);

        var passwordMatches = passwordEncoder.matches(candidateLoginRequest.getPassword(), targetCandidate.getPassword());

        if (!passwordMatches) {
            throw new CandidateNotFoundException();
        }

        var tokens = jwtService.createTokens(targetCandidate.getId(), RoleType.Candidate.name());

        return new CandidateAuthResponse(tokens, targetCandidate.toCandidateDomain().toCandidateResponse());

    }

    public CompanyAuthResponse loginCompany(CompanyLoginRequest companyLoginRequest) {

        var targetCompany = companyRepository.findByCnpj(companyLoginRequest.getCnpj())
                .orElseThrow(CompanyNotFoundException::new);

        var passwordMatches = passwordEncoder.matches(companyLoginRequest.getPassword(), targetCompany.getPassword());

        if (!passwordMatches) {
            throw new CompanyNotFoundException();
        }

        var tokens = jwtService.createTokens(targetCompany.getId(), RoleType.Company.name());

        return new CompanyAuthResponse(tokens, targetCompany.toCompanyDomain().toCompanyResponse());

    }

}
