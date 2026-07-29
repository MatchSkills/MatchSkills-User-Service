package com.matchskills.user.service.exceptions.customs;

public class CandidateAlreadyExistsException extends RuntimeException {
    public CandidateAlreadyExistsException() {
        super("a candidate already exists with this email");
    }
}
