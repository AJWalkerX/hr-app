package com.ajwalker.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Service
public class JwtManager {
    @Value("56f71ab4bb5e1dae6d6653244d321a3e")
    private String secretKey;
    @Value("ZorIK")
    private String issuer;
    private final Long ExDate = 1000L * 40;

    public String createToken(Long authId){
        Date createDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + ExDate);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withAudience()
                .withIssuer(issuer)
                .withIssuedAt(createDate)
                .withExpiresAt(expirationDate)
                .withClaim("authId", authId)
                .withClaim("key", "KIK13")
                .sign(algorithm);
    }
    public Optional<Long> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(Objects.isNull(decodedJWT)){
                return Optional.empty();
            }
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
