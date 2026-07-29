package com.matchskills.user.service.services;

import com.matchskills.user.service.dtos.CandidateResponse;
import com.matchskills.user.service.dtos.CreateCandidateRequest;
import com.matchskills.user.service.dtos.EditCandidateResponse;
import com.matchskills.user.service.entitys.CandidateEntity;
import com.matchskills.user.service.exceptions.customs.CandidateAlreadyExistsException;
import com.matchskills.user.service.exceptions.customs.CandidateNotFoundException;
import com.matchskills.user.service.repositorys.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateResponse getCandidate(Long id){

        var savedCandidate = candidateRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        return savedCandidate.toCandidateDomain().toCandidateResponse();

    }

    public void createCandidate(CreateCandidateRequest candidateRequest){

        var alreadyExists = candidateRepository.existsByEmail(candidateRequest.getEmail());

        if(alreadyExists){
            throw new CandidateAlreadyExistsException();
        }

        //TODO put a password encrypt when auth exists

        var newCandidate = CandidateEntity.builder()
                .name(candidateRequest.getName())
                .email(candidateRequest.getEmail())
                .password(candidateRequest.getPassword())
                .number(candidateRequest.getNumber())
                .build();

        candidateRepository.save(newCandidate);

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

        candidateRepository.deleteById(id);

    }

}
