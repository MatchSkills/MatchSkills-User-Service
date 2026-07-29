package com.matchskills.user.service.exceptions.customs.company;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException() {
        super("a candidate already exists with this cnpj");
    }
}
