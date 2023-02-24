package com.ecommerce.authservices.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.authservices.security.SecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtility {
    public String generateJwtToken(Authentication authentication) {

        String token = JWT.create()
                .withSubject(authentication.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        return token;

    }

    public String getUsernameFromJwtToken(String token) {
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
        return user;
    }

    public boolean validateJwtToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
