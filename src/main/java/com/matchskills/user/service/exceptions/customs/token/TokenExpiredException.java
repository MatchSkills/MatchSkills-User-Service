package com.matchskills.user.service.exceptions.customs.token;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("Token is expired");
    }

    public TokenExpiredException(String message) {
        super(message);
    }

}
