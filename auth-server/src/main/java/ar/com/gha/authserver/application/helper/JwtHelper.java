package ar.com.gha.authserver.application.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtHelper {

    @Value("${application.jwt.secret}")
    private String jwtSecret;

    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(this.getSecretKey())
                .compact();
    }

    public void validateToken(String token) {
        final var expirationDate = this.getExpirationDateFromToken(token);
        if (expirationDate.before(new Date())) {
            log.error("El token ha expirado");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El token ha expirado");
        }

    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {

        return claimsResolver.apply(this.signToken(token));
    }

    private Claims signToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(this.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
