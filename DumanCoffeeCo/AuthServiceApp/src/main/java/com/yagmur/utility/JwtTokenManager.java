package com.yagmur.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yagmur.exception.AuthManagerException;
import com.yagmur.exception.ErrorType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    private final String SECRETKEY = "b>t$[97£7PF=p.";
    private final String ISSUER = "CDDEY";
    private final Long EXDATE = 1000L * 120;

    public Optional<String> createToken(Long id){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token = JWT.create()
                    .withAudience()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> createToken(String id, ERole role){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(1000*60*10));
        try {
            token = JWT.create()
                    .withAudience()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).withAudience().build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                return false;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
        return true;
    }

    public Optional<Long> getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                throw new AuthManagerException(ErrorType.INVALID_TOKEN);
            }
            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                throw new AuthManagerException(ErrorType.INVALID_TOKEN);
            }
            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
    }
}
