package com.uberApplication.uber.Security;

import com.uberApplication.uber.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}") // link of the secret key from application.properties
    private String jwtSecretKey;

    private SecretKey getSecretKey() { // method to get the secret key by a method so this method can be use directly
        // in signWith()
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

    }

    public String generateAccessTokens(User user) {

        return Jwts.builder()
                .subject(user.getId().toString()) // define the subject
                .claim("email", user.getEmail()) // defining the emails claim
                .claim("roles", user.getRoles().toString()) // defining the user
                .issuedAt(new Date()) // creating date of this token
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // defining the expiry date of this token
                .signWith(getSecretKey()) // use the getSecretKey() which we made for get the secret key
                .compact();

    }

    public String generateRefreshTokens(User user) {

        return Jwts.builder()
                .subject(user.getId().toString()) // define the subject

                .issuedAt(new Date()) // creating date of this token
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6)) // defining the expiry
                // date of this token
                .signWith(getSecretKey()) // use the getSecretKey() which we made for get the secret key
                .compact();

    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

}