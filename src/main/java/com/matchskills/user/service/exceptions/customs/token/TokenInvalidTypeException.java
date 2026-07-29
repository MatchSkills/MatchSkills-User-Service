package com.matchskills.user.service.exceptions.customs.token;

public class TokenInvalidTypeException extends RuntimeException {
    public TokenInvalidTypeException(String message) {
        super("The type of this token is invalid, is required: " + message );
    }
}
