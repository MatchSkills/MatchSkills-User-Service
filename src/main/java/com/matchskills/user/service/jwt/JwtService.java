package com.matchskills.user.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.matchskills.user.service.domains.TokenDecoded;
import com.matchskills.user.service.dtos.tokens.TokensResponse;
import com.matchskills.user.service.exceptions.customs.token.TokenExpiredException;
import com.matchskills.user.service.exceptions.customs.token.TokenInvalidException;
import com.matchskills.user.service.exceptions.customs.token.TokenInvalidTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class JwtService {

    private final Algorithm algorithm;
    private final String issuer;
    private final String audience;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.audience}") String audience
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.audience = audience;
    }

    public String createAccessToken(Long id, String role){

        Instant expireTimeAccess = Instant.now()
                .plus(Duration.ofMinutes(15));

        String accessTokenId = UUID.randomUUID().toString();

        return JWT.create()
                .withJWTId(accessTokenId)
                .withClaim("id", id)
                .withClaim("role", role)
                .withClaim("type", "Access")
                .withExpiresAt(expireTimeAccess)
                .withAudience(audience)
                .withIssuer(issuer)
                .sign(algorithm);

    }

    public String createRefreshToken(Long id, String role){

        Instant expireTimeRefresh = Instant.now()
                .plus(Duration.ofDays(7));

        String refreshTokenId = UUID.randomUUID().toString();

        return JWT.create()
                .withJWTId(refreshTokenId)
                .withClaim("id", id)
                .withClaim("role", role)
                .withClaim("type", "Refresh")
                .withExpiresAt(expireTimeRefresh)
                .withAudience(audience)
                .withIssuer(issuer)
                .sign(algorithm);

    }

    public TokensResponse createTokens(Long id, String role){

        return new TokensResponse(
                createAccessToken(id,role),createRefreshToken(id, role)
        );
    }

    public TokenDecoded decodeAccessToken(String token){

        try{

            DecodedJWT verifier = JWT.require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            if (!verifier.getClaim("type").asString().equals("Access")){
                throw new TokenInvalidTypeException("Access");
            }

            return new TokenDecoded(verifier.getId(),verifier.getClaim("id").asLong(), verifier.getClaim("role").asString());

        } catch (com.auth0.jwt.exceptions.TokenExpiredException exception){
            throw new TokenExpiredException();
        } catch (JWTVerificationException exception){
            throw new TokenInvalidException();
        }

    }

    public TokenDecoded decodeRefreshToken(String token){

        try{

            DecodedJWT verifier = JWT.require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            if (!verifier.getClaim("type").asString().equals("Refresh")){
                throw new TokenInvalidTypeException("Refresh");
            }

            return new TokenDecoded(verifier.getId(),verifier.getClaim("id").asLong(), verifier.getClaim("role").asString());

        } catch (com.auth0.jwt.exceptions.TokenExpiredException exception){
            System.out.println(exception.getMessage());
            throw new TokenExpiredException();
        } catch (JWTVerificationException exception){
            throw new TokenInvalidException();
        }

    }

    public String getTokenId(String token){

        try {

            DecodedJWT decoded = JWT.decode(token);

            return decoded.getId();

        } catch (JWTDecodeException exception){
            throw new TokenInvalidException();
        }


    }

    public String getToken(String rawToken){

        if (!rawToken.startsWith("Bearer ")){
            throw new TokenInvalidException();
        }

        return rawToken.replace("Bearer ", "");

    }

}
