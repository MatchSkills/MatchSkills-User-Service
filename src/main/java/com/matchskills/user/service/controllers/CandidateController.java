package com.matchskills.user.service.controllers;

import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import com.matchskills.user.service.dtos.candidate.EditCandidateResponse;
import com.matchskills.user.service.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    final private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidate(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getCandidate(id));
    }

//    @PostMapping
//    public ResponseEntity<CandidateResponse> createCandidate(@Valid @RequestBody CreateCandidateRequest candidate) {
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.createCandidate(candidate));
//    }

    @PutMapping
    @PreAuthorize("hasRole('Candidate')")
    public ResponseEntity<CandidateResponse> editCandidate(@Valid @RequestBody EditCandidateResponse candidate) {

        return ResponseEntity.status(HttpStatus.OK).body(candidateService.editCandidate(candidate));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Candidate')")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {

        candidateService.deleteCandidate(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
