package com.ecommerce.authservices.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
// if you implement generic Filter use following code
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        //typecast servletRequest to HttpServletRequest and get the path
//        ((HttpServletRequest)servletRequest).getRequestURI();
//        filterChain.doFilter(servletRequest,servletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
             try{
                filterChain.doFilter(request,response);
             }catch (RuntimeException e) {
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             }
    }
}
