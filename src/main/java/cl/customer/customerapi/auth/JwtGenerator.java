package cl.customer.customerapi.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cl.customer.customerapi.exeption.authex.JwtAuthenticationException;

@Component
public class JwtGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JwtGenerator.class);

    @Value(value = "${app.jwt.secret-key}")
    private String secretKey;    
    
    private static final long EXPIRATION_TIME = 900_000; // 15 minutes in milliseconds

    public String generateToken(Authentication authentication) {   
        return getString(authentication);
    }

    private String getString(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(getKey())
                .compact();
    }    

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromJWT(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío {}", e.getMessage());
            return false;
        }
        return false;
    }

    public String refreshToken(Authentication authentication) {
        try {
            return getString(authentication);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Error internal server");
        }
    }



}
