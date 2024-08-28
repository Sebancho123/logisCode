package com.logistica.logisCode.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.private.generator}")
    private String userGenerator;

    //creacion de tokens
    public String createToken(Authentication authentication) {

        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        //qda en en context holder
        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (30 * 60000)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;

    }

    //descodificar y validar nuestros tokens
    public DecodedJWT validateToken(String token){

        try {

            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            //si tood esta bien no nos tira ecxepciones y nos devuelve el jwt descodificado

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }catch (JWTVerificationException exception) {
            throw new JWTVerificationException("invalid token not authorise");
        }

    }

    //metodo para obtener el usuario/username
    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().toString();
    }

    //metodo para obtener un claim en particular
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){

        return decodedJWT.getClaim(claimName);

    }

    //metodo para obtenmer todos los claaims
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
