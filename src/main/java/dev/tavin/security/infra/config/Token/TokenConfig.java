package dev.tavin.security.infra.config.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tavin.security.infra.dto.AuthDto.JWTUserData;
import dev.tavin.security.infra.entity.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenConfig {

    @Value("${app.security.jwt-secret}")
    private String secret;

    private final Algorithm algorithm;

    public TokenConfig(@Value("${app.security.jwt-secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(UserModel user){

        return JWT.create()
                .withClaim("userId", user.getId().toString())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try{
            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(UUID.fromString(decode.getClaim("userId").asString()))
                    .email(decode.getSubject())
                    .build());

        }  catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
