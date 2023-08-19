package com.jiat.crud_application.util;

import com.jiat.crud_application.entity.User;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_PASSWORD = "sub2";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String ISSUER = "www.jiat.lk";
    private static final String SECRET = "KmF^HZdGNeu^^aWM%8ZyxUk$9EKnAmfAx#Ol86<j9L!g7O^Th50zCQoX&nUUt1Y2";
    private static final Long TOKEN_LIFE = 30L;
    private static final Long REFRESH_TOKEN_LIFE = 43200L;

    private String generateToken(Map<String, String> claims, Long expiration, String subject, String subject2) {
        Signer signer = HMACSigner.newSHA256Signer(SECRET);

        JWT jwt = new JWT().setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(subject)
                .setSubject(subject2)
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(expiration));

        claims.keySet().forEach(k -> {
            if (claims.get(k) != null) {
                jwt.addClaim(k, claims.get(k));
            }
        });
        String encodedJWT = JWT.getEncoder().encode(jwt, signer);
        return encodedJWT;
    }

    public Map<String, String> getClaimsFromToken(String token) {
        Verifier verifier = HMACVerifier.newVerifier(SECRET);

        JWT jwt = JWT.getDecoder().decode(token, verifier);

        Map<String, String> claims = new HashMap<>();
        if (jwt != null) {
            jwt.getAllClaims().forEach((k, v) -> {
                claims.put(k, v.toString());
            });
        }
        return claims;
    }

    public String getNameFromToken(String token) {
        Map<String, String> claims = getClaimsFromToken(token);
        return claims.get(CLAIM_KEY_USERNAME);
    }
    public String getPasswordFromToken(String token) {
        Map<String, String> claims = getClaimsFromToken(token);
        return claims.get(CLAIM_KEY_PASSWORD);
    }

    public Date getExpiredDateFromToken(String token) {
        Verifier verifier = HMACVerifier.newVerifier(SECRET);
        JWT jwt = JWT.getDecoder().decode(token, verifier);

        return new Date(jwt.expiration.toInstant().toEpochMilli());
    }

    public boolean isTokenExpired(String token){
        Date expireDateOfToken = getExpiredDateFromToken(token);
        return expireDateOfToken.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token, User user) {
        String username = getNameFromToken(token);
        String password = getPasswordFromToken(token);
        return username.equals(user.getName()) && password.equals(user.getPassword())&& !isTokenExpired(token);
    }

    public String generateAccessToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getName());
        claims.put(CLAIM_KEY_PASSWORD, user.getPassword());
        claims.put(CLAIM_KEY_CREATED, new Date().toString());
        return generateToken(claims, TOKEN_LIFE, user.getName(),user.getPassword());
    }
    public String generateRefreshToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, user.getName());
        claims.put(CLAIM_KEY_PASSWORD, user.getPassword());
        claims.put(CLAIM_KEY_CREATED, new Date().toString());
        return generateToken(claims, REFRESH_TOKEN_LIFE, user.getName(), user.getPassword());
    }

}