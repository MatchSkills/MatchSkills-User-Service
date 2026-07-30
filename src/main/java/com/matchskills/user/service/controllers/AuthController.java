package com.matchskills.user.service.controllers;

import com.matchskills.user.service.dtos.auth.*;
import com.matchskills.user.service.dtos.candidate.CreateCandidateRequest;
import com.matchskills.user.service.dtos.company.CreateCompanyRequest;
import com.matchskills.user.service.dtos.tokens.TokensRequest;
import com.matchskills.user.service.dtos.tokens.TokensResponse;
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

    @PostMapping("/login/candidate")
    public  ResponseEntity<CandidateAuthResponse> loginCandidate(@Valid @RequestBody CandidateLoginRequest candidateLoginRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(authService.loginCandidate(candidateLoginRequest));

    }

    @PostMapping("/login/company")
    public ResponseEntity<CompanyAuthResponse> loginCompany(@Valid @RequestBody CompanyLoginRequest companyLoginRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(authService.loginCompany(companyLoginRequest));

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestHeader("Authorization") String accesstoken, @RequestBody LogoutRequest refreshToken){

        authService.logout(new TokensRequest(accesstoken,refreshToken.getRefreshToken()));

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokensResponse> refresh(@RequestHeader("Authorization") String token){

        return ResponseEntity.status(HttpStatus.OK).body(authService.refresh(token));
    }

}
