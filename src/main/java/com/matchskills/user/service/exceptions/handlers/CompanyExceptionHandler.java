package com.matchskills.user.service.exceptions.handlers;

import com.matchskills.user.service.exceptions.CustomErrorResponse;
import com.matchskills.user.service.exceptions.customs.company.CompanyAlreadyExistsException;
import com.matchskills.user.service.exceptions.customs.company.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CompanyExceptionHandler {

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleCompanyAlreadyExistsException(CompanyAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomErrorResponse(ex.getMessage(),409));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(ex.getMessage(),404));
    }

}
