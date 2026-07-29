package com.matchskills.user.service.exceptions.customs.token;

public class TokenInBlackListException extends RuntimeException {
    public TokenInBlackListException() {
        super("The token is blacklisted.");
    }
}
