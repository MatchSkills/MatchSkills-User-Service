package com.matchskills.user.service.controllers;

import com.matchskills.user.service.dtos.CandidateResponse;
import com.matchskills.user.service.dtos.CreateCandidateRequest;
import com.matchskills.user.service.dtos.EditCandidateResponse;
import com.matchskills.user.service.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Void> createCandidate(@Valid @RequestBody CreateCandidateRequest candidate) {

        candidateService.createCandidate(candidate);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> editCandidate(@Valid @RequestBody EditCandidateResponse candidate) {

        candidateService.editCandidate(candidate);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {

        candidateService.deleteCandidate(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
