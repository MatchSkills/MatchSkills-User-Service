package com.matchskills.user.service.services;

import com.matchskills.user.service.dtos.auth.CandidateAuthResponse;
import com.matchskills.user.service.dtos.auth.CompanyAuthResponse;
import com.matchskills.user.service.dtos.candidate.CreateCandidateRequest;
import com.matchskills.user.service.dtos.company.CreateCompanyRequest;
import com.matchskills.user.service.enums.RoleType;
import com.matchskills.user.service.jwt.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    final private CandidateService candidateService;
    final private CompanyService companyService;
    final private JwtService jwtService;

    public  AuthService(CandidateService candidateService, CompanyService companyService, JwtService jwtService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.jwtService = jwtService;
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

}
