package com.ecommerce.productservices.security.filter;


import com.ecommerce.productservices.config.APIConfig;
import com.ecommerce.productservices.config.AppConfig;
import com.ecommerce.productservices.feignclients.CustomFeignClient;
import com.ecommerce.productservices.model.UserLogin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    //@Autowired
    private RestTemplate restTemplate;
    private APIConfig apiConfig;
    private CustomFeignClient customFeignClient;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("ApiConfig: {}", apiConfig.getAuthBaseURL());
        String header = request.getHeader("Authorization");// Bearer JWT token

        //if client is registering for the first time we do not have JWT token so no need to verify
        if (header == null || !header.startsWith("Bearer ")) {
            log.error("Request received without Authorization header: {}", request);
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", ""); // JWT token

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", header);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

        try {
            String output = restTemplate.exchange(apiConfig.getAuthBaseURL(), HttpMethod.GET, entity, String.class).getBody();
            // System.out.println("output:" + output);

           //call to verify the token
            if(output.equals("Token is fine.")) {
                //get user from token
                //String user = restTemplate.exchange("http://localhost:9897/auth/getUserFromToken", HttpMethod.GET,entity, String.class).getBody();

                UserLogin user = customFeignClient.getUserFromToken(header);
                String userAuth = customFeignClient.getAuthorityFromToken(header);

                List<GrantedAuthority> authorities = new ArrayList<>();
                //authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleName().toUpperCase()));
                authorities.add(new SimpleGrantedAuthority(userAuth));

                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.error("Token isn't fine");
            }
        } catch (RuntimeException e) {
            log.error("Sorry! Invalid token. Ran into exception: {}", e.getMessage());
            throw e;
        }
        filterChain.doFilter(request, response);


    }
}
