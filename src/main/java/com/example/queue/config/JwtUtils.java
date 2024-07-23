package com.example.queue.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    @Value("${jwt.secret}")
    private String key;

    public String generateToken(String username) {
        return JWT.create()
                .withIssuedAt(new Date())
                .withIssuer("daniel_tamoe")
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMonths(1).toInstant()))
                .withClaim("username", username)
                .withSubject("User details")
                .sign(Algorithm.HMAC512(key));
    }

    public String testingToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(key))
                .withIssuer("daniel_tamoe")
                .withSubject("User details")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }
}