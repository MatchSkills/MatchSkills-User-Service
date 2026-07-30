package com.matchskills.user.service.controllers;

import com.matchskills.user.service.dtos.auth.CandidateAuthResponse;
import com.matchskills.user.service.dtos.auth.CompanyAuthResponse;
import com.matchskills.user.service.dtos.candidate.CreateCandidateRequest;
import com.matchskills.user.service.dtos.company.CreateCompanyRequest;
import com.matchskills.user.service.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/candidate")
    public ResponseEntity<CandidateAuthResponse> registerCandidate(@Valid @RequestBody CreateCandidateRequest createCandidateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerCandidate(createCandidateRequest));

    }

    @PostMapping("/register/company")
    public ResponseEntity<CompanyAuthResponse> registerCompany(@Valid @RequestBody CreateCompanyRequest createCompanyRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerCompany(createCompanyRequest));

    }

}
