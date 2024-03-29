package br.com.gcm.sac.setor_armamento.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.gcm.sac.setor_armamento.exceptions.TokenInvalidException;
import br.com.gcm.sac.setor_armamento.model.UserModel;

@Service
public class TokenService {

    public String gerarToken(UserModel userModel){
        return JWT.create()
                .withIssuer("Gcm")
                .withSubject((userModel.getUsername()))
                .withClaim("id", userModel.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        try{
            return JWT.require(Algorithm.HMAC256("secreta"))
            .withIssuer("Gcm")
            .build()
            .verify(token)
            .getSubject();
        }catch(JWTVerificationException exception){
            throw new TokenInvalidException("Token JWT inválido ou expirado ");
        }
    }
}
