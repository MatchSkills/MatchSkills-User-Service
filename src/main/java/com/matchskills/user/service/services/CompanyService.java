package com.matchskills.user.service.services;

import com.matchskills.user.service.dtos.company.CompanyResponse;
import com.matchskills.user.service.dtos.company.CreateCompanyRequest;
import com.matchskills.user.service.dtos.company.EditCompanyRequest;
import com.matchskills.user.service.entitys.CompanyEntity;
import com.matchskills.user.service.exceptions.customs.company.CompanyAlreadyExistsException;
import com.matchskills.user.service.exceptions.customs.company.CompanyNotFoundException;
import com.matchskills.user.service.repositorys.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    final private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse getCompany(Long id){

        var savedCompany = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);

        return savedCompany.toCompanyDomain().toCompanyResponse();

    }

    public CompanyResponse createCompany(CreateCompanyRequest createCompanyRequest) {

        var existsCompany = companyRepository.existsByCnpj(createCompanyRequest.getCnpj());

        if (existsCompany) {
            throw new CompanyAlreadyExistsException();
        }

        //TODO put a password encrypt when auth exists

        var newCompany = CompanyEntity.builder()
                .name(createCompanyRequest.getName())
                .cnpj(createCompanyRequest.getCnpj())
                .email(createCompanyRequest.getEmail())
                .password(createCompanyRequest.getPassword())
                .address(createCompanyRequest.getAddress())
                .build();

        var savedCompany = companyRepository.save(newCompany);

        return savedCompany.toCompanyDomain().toCompanyResponse();

    }

    public CompanyResponse editCompany(EditCompanyRequest editCompanyRequest) {

        var targetCompany = companyRepository.findById(editCompanyRequest.getId())
                .orElseThrow(CompanyNotFoundException::new);

        targetCompany.setName(editCompanyRequest.getName());
        targetCompany.setEmail(editCompanyRequest.getEmail());
        targetCompany.setAddress(editCompanyRequest.getAddress());

        var saved = companyRepository.save(targetCompany);

        return saved.toCompanyDomain().toCompanyResponse();

    }

    public void deleteCompany(Long id){

        var existsCompany = companyRepository.existsById(id);

        if (!existsCompany) {
            throw new CompanyNotFoundException();
        }

        //TODO delete all jobposting

        companyRepository.deleteById(id);

    }

}
