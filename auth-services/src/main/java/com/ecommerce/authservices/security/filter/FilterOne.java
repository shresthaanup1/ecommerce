package com.ecommerce.authservices.security.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterOne implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //typecast servletRequest to HttpServletRequest and get the path
        ((HttpServletRequest)servletRequest).getRequestURI();
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
