package org.example.sweetshopmanagement.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private final String secret="+cGZ+VoxprjII5f3qQcfwHp3JAz0bvPOtRb2U/bLmpo=";
    public String generateToken(String username, String role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", "ROLE_" + role)   // FIXED
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public String extractRole(String token){
        return (String)  getClaims(token).get("role");
    }
    public boolean validateToken(String token){
        try{
            getClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
