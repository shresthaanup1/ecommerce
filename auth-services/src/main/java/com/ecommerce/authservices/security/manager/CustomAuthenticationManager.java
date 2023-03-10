package com.ecommerce.authservices.security.manager;

import com.ecommerce.authservices.feignclients.CustomFeignClient;
import com.ecommerce.authservices.model.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private CustomFeignClient customFeignClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserLogin userFromDb =customFeignClient.getUserLoginByUsername(authentication.getPrincipal().toString());
        System.out.println(customFeignClient.getUserLoginByUsername(authentication.getPrincipal().toString()).getPassword());


        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userFromDb.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), userFromDb.getPassword());
    }
}