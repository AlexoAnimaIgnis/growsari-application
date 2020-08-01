package com.growsari.application.server.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class GrowsariBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private static final String REALM_NAME = "GROWSARI";
    
    public GrowsariBasicAuthenticationEntryPoint() {
        setRealmName(REALM_NAME);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
