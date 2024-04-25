package cl.customer.customerapi.config;

import io.jsonwebtoken.*;

import java.util.Date;

import javax.crypto.SecretKey;

public class JwtUtil {
    
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }
}
