package com.rutrob.recipesapi.security;

import com.rutrob.recipesapi.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generate(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        byte[] signingKey = jwtSecret.getBytes();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .subject(user.getUsername())
                .claim("roles", roles)
                .compact();
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        byte[] signingKey = jwtSecret.getBytes();

        try {
            return Optional.of(Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(signingKey))
                    .build()
                    .parseSignedClaims(token));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
