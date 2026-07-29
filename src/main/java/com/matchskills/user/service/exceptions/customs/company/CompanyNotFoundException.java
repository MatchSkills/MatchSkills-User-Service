package com.matchskills.user.service.exceptions.customs.company;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company Not Found");
    }
}
