package com.ecommerce.authservices.security.manager;

import lombok.AllArgsConstructor;
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

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("admin").password("test").roles("ADMIN").build();
        UserDetails user2 = User.withUsername("user").password("test1").roles("USER").build();
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);

        UserDetails userFromDb = userDetailsManager.loadUserByUsername(authentication.getPrincipal().toString());


        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), bCryptPasswordEncoder.encode(userFromDb.getPassword()))) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), userFromDb.getPassword());
    }
}