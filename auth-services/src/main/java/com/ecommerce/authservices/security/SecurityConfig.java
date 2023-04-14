package com.ecommerce.authservices.security;

import com.ecommerce.authservices.security.filter.AuthenticationFilter;
import com.ecommerce.authservices.security.filter.ExceptionHandlerFilter;
import com.ecommerce.authservices.security.filter.JWTAuthorizationFilter;
import com.ecommerce.authservices.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        //change the default url login to authenticate
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                //.addFilterBefore(new ExceptionHandlerFilter(),AuthenticationFilter.class)
                //.addFilter(authenticationFilter)
                //.addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


}
