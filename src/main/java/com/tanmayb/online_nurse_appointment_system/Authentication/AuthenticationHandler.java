package com.tanmayb.online_nurse_appointment_system.Authentication;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("authenticationHandler")
public class AuthenticationHandler implements AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        
        response.setContentType("application/json");
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        response.getOutputStream().println("Error: Login credentials required.");
        
    }
    
}