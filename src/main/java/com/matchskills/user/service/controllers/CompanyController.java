package com.matchskills.user.service.controllers;

import com.matchskills.user.service.dtos.company.CompanyResponse;
import com.matchskills.user.service.dtos.company.EditCompanyRequest;
import com.matchskills.user.service.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    final private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(id));
    }

//    @PostMapping
//    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CreateCompanyRequest createCompanyRequest) {
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.createCompany(createCompanyRequest));
//
//    }

    @PutMapping
    @PreAuthorize("hasRole('Company')")
    public ResponseEntity<CompanyResponse> editCompany(@Valid @RequestBody EditCompanyRequest editCompanyRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(this.companyService.editCompany(editCompanyRequest));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Company')")
    public ResponseEntity<CompanyResponse> deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);

        //TODO delete all jobposting

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }
}
