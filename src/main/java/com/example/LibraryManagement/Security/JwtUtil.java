package com.example.LibraryManagement.Security;

import com.example.LibraryManagement.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtUtil {


    private final String secretKey;


    public JwtUtil(
            @Value("${jwt.secret_key}") String secretKey
    ) {
        this.secretKey = secretKey;
    }

    private SecretKey getKey(){

        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }


    public String generateToken(User user){

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role",user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(getKey())
                .compact();

    }


    public String extractUsername(String token){

        return extractClaims(token)
                .getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username  = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }


    public Boolean isTokenExpired(String token){

        return extractClaims(token).getExpiration().before(new Date());
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}