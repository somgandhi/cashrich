package com.cashrich.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PredefinedHeaderFilter extends HttpFilter {

    private static final String PREDEFINED_HEADER_KEY = "PRE-DEFINED-HEADER-KEY";
    private static final String PREDEFINED_HEADER_VALUE = "PRE-DEFINED-HEADER-VALUE";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(PREDEFINED_HEADER_KEY);
        if ( !PREDEFINED_HEADER_VALUE.equals(header)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Kindly pass correct Header Key");
        }
        chain.doFilter(request,response);
    }
}
