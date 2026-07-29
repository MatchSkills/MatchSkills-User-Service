package com.matchskills.user.service.exceptions.handlers;

import com.matchskills.user.service.exceptions.CustomErrorResponse;
import com.matchskills.user.service.exceptions.customs.CandidateAlreadyExistsException;
import com.matchskills.user.service.exceptions.customs.CandidateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CandidatesExceptionHandler {

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCandidateNotFoundException(CandidateNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(e.getMessage(),404));
    }

    @ExceptionHandler(CandidateAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleCandidateAlreadyExistsException(CandidateAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomErrorResponse(e.getMessage(),409));
    }

}
