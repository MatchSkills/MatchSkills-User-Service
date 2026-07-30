package com.matchskills.user.service.controllers;

import com.matchskills.user.service.enums.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerController {

    @GetMapping
    public ResponseEntity<Void> health() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("test/company")
    @PreAuthorize("hasRole('Company')")
    public String testRoleCompany(){
        return "ok";
    }

    @GetMapping("test/candidate")
    @PreAuthorize("hasRole('Candidate')")
    public String testRoleCandidate(){
        return "ok";
    }

}
