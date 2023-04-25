package com.ecommerce.productservices.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
             try{
                filterChain.doFilter(request,response);
             }catch (RuntimeException e) {
                 logger.error("Ran into exception", e);
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             }
    }
}
