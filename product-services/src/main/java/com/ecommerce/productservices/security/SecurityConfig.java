package com.ecommerce.productservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Configuration
    public class SecurityConfiguration {
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           http
                   //as our api will be called by other web application so zero chances of browser attack
                   .csrf().disable()
                   //every request need to be authorized to be accessed
                   .authorizeRequests()
                   //all http method DELETE require admin role
                   .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                   .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN","USER")
                   .antMatchers(HttpMethod.GET).permitAll()
                   .anyRequest().authenticated()
                   .and()
                   .httpBasic()
                   .and()
                   //do not store username and password in session as cookie
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           return http.build();
        }

        @Bean
        public UserDetailsService users() {
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(bCryptPasswordEncoder.encode("test"))
                    .roles("ADMIN")
                    .build();
            UserDetails user = User.builder()
                    .username("user")
                    .password(bCryptPasswordEncoder.encode("test1"))
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(admin, user);

        }

    }
}
