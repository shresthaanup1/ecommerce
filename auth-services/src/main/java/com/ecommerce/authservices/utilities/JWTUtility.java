package com.ecommerce.authservices.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.authservices.security.SecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtility {
    public String generateJwtToken(Authentication authentication) {

        String token = JWT.create()
                .withSubject(authentication.getPrincipal().toString())
                .withArrayClaim("authorities", getAuthoritiesArray(authentication.getAuthorities()))
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

    public String getAuthorityFromJwtToken(String token) {
        List<String> claims = new ArrayList<>();
        claims = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                .build()
                .verify(token)
                .getClaim("authorities").asList(String.class);
        //System.out.println(claims.get(0));

        return claims.get(0);
    }

    public boolean validateJwtToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid token");
        }
        return false;
    }

    private static String[] getAuthoritiesArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> authorityList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            authorityList.add(authority.getAuthority());
        }
        return authorityList.toArray(new String[0]);
    }
}
