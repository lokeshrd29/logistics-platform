package com.lokesh.logistics.auth_service.security;

import com.lokesh.logistics.auth_service.entity.Role;
import com.lokesh.logistics.auth_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // 256-bit hexadecimal secret key secure enough for HS256 encryption
    private static final String SECRET_KEY = "9a72dd7322d7de629fbfb14e9f939e044c3bb31e5f03d21c251d18bb7201c9a6";

    private static final long accessTokenExpiration = 900000;
    private static final long refreshTokenExpiration = 604800000;


    public String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpiration);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpiration);
    }

    private String generateToken(User user, long expiration){

            Map<String, Object> claims = new HashMap<>();

            claims.put("userId", user.getId());
            claims.put("roles",
                user.getRoles()
                        .stream()
                        .map(Role::getRole)
                        .toList());

            return Jwts.builder()
                    .claims(claims)
                    .subject(user.getUsername())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(getSigningKey())
                    .compact();
            }
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long getAccessTokenExpiration() {
        return accessTokenExpiration ;
    }

    //----------------------------------------------------------------------------------------//
}
