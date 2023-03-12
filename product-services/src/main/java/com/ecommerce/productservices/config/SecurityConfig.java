package com.ecommerce.productservices.config;

import com.ecommerce.productservices.feignclients.CustomFeignClient;
import com.ecommerce.productservices.security.filter.ExceptionHandlerFilter;
import com.ecommerce.productservices.security.filter.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class SecurityConfig {


    private APIConfig apiConfig;
    private RestTemplate restTemplate;

    private CustomFeignClient customFeignClient;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST).permitAll()
                //.antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
                //.addFilter(authenticationFilter)
                //.addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(restTemplate,apiConfig,customFeignClient), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


}