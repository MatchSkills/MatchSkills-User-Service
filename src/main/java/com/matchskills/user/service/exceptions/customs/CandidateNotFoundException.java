package com.matchskills.user.service.exceptions.customs;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super("Candidate Not Found");
    }
}
