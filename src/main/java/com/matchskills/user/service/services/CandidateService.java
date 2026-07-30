package com.matchskills.user.service.services;

import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import com.matchskills.user.service.dtos.candidate.CreateCandidateRequest;
import com.matchskills.user.service.dtos.candidate.EditCandidateResponse;
import com.matchskills.user.service.entitys.CandidateEntity;
import com.matchskills.user.service.exceptions.customs.candidate.CandidateAlreadyExistsException;
import com.matchskills.user.service.exceptions.customs.candidate.CandidateNotFoundException;
import com.matchskills.user.service.repositorys.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateResponse getCandidate(Long id){

        var savedCandidate = candidateRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        return savedCandidate.toCandidateDomain().toCandidateResponse();

    }

    public CandidateResponse createCandidate(CreateCandidateRequest candidateRequest){

        var alreadyExists = candidateRepository.existsByEmail(candidateRequest.getEmail());

        if(alreadyExists){
            throw new CandidateAlreadyExistsException();
        }

        var encriptedPassword = passwordEncoder.encode(candidateRequest.getPassword());

        var newCandidate = CandidateEntity.builder()
                .name(candidateRequest.getName())
                .email(candidateRequest.getEmail())
                .password(encriptedPassword)
                .number(candidateRequest.getNumber())
                .build();

        var savedCandidate = candidateRepository.save(newCandidate);

        return savedCandidate.toCandidateDomain().toCandidateResponse() ;

    }

    public CandidateResponse editCandidate(EditCandidateResponse candidate) {

        var targetCandidate = candidateRepository.findById(candidate.getId())
                .orElseThrow(CandidateNotFoundException::new);

        targetCandidate.setName(candidate.getName());
        targetCandidate.setEmail(candidate.getEmail());
        targetCandidate.setNumber(candidate.getNumber());

        var savedCandidate = candidateRepository.save(targetCandidate);

        return  savedCandidate.toCandidateDomain().toCandidateResponse();

    }

    public void deleteCandidate(Long id){

        var alreadyExists = candidateRepository.existsById(id);

        if(!alreadyExists){
            throw new CandidateNotFoundException();
        }

        //TODO delete all jobApplication

        candidateRepository.deleteById(id);

    }

}
