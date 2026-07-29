package com.matchskills.user.service.exceptions.customs.token;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException() {
        super("Token is invalid");
    }
}
