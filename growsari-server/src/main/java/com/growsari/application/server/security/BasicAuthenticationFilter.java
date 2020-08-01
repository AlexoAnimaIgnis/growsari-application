package com.growsari.application.server.security;

import com.growsari.application.server.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationFilter.class);

    private static final String UTF_8 = "UTF-8";
    private static final String COLON = ":";
    private static final int NOT_EXISTS = -1;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";
    private static final String RESPONSE_HEADER_UNSUCCESSFUL_AUTHENTICATION = "User-Authentication-Failed";

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthenticationManager authenticationManager;
    private RememberMeServices rememberMeServices = new NullRememberMeServices();
    private String credentialsCharset = UTF_8;
    private UserService userService;

    public BasicAuthenticationFilter(AuthenticationManager authenticationManager,
                                     AuthenticationEntryPoint authenticationEntryPoint,
                                     UserService userService) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userService = userService;

    }

    @Override
    public void afterPropertiesSet()  {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");
        Assert.notNull(this.authenticationEntryPoint, "An AuthenticationEntryPoint is required");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull  HttpServletResponse response,
                                    @Nonnull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        String username = null;

        if (header == null || !header.startsWith(BASIC_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String[] tokens = extractAndDecodeHeader(header);
            assert tokens.length == 2;

            username = tokens[0];
            String password = tokens[1];

            if (authenticationIsRequired(username, password)) {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        username, password
                );
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                Authentication authResult = authenticationManager.authenticate(authRequest);

                SecurityContextHolder.getContext().setAuthentication(authResult);

                rememberMeServices.loginSuccess(request, response, authResult);

                onSuccessfulAuthentication(request, response, authResult);
            }
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();

            rememberMeServices.loginFail(request, response);

            onUnsuccessfulAuthentication(request, response, e, username);
            authenticationEntryPoint.commence(request, response, e);

            return;

        }

        filterChain.doFilter(request, response);
    }

    private String[] extractAndDecodeHeader(String header) throws IOException {
        byte[] decoded = decodeHeader(header);
        String token = new String(decoded, credentialsCharset);
        int delim = token.indexOf(COLON);

        if (delim == NOT_EXISTS) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    private byte[] decodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);

        try {
            return Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
    }

    private boolean authenticationIsRequired(String username, String password) {
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = existingAuth != null ? (UserDetails) existingAuth.getPrincipal() : null;

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username)) {
            return true;
        }

        //check password
        try {
            boolean authenticated = userDetails != null && (userDetails.getPassword() != null ?
                    userDetails.getPassword().equals(password) :
                    password == null
            );

            if (!authenticated) {
                return true;
            }
        } catch (Throwable exc) {
            logger.error("Checking password failed", exc);
            return true;
        }

        return existingAuth instanceof AnonymousAuthenticationToken;
    }

    private void onSuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authResult) {
        logger.info("Successful authentication of: " + authResult);
    }

    private void onUnsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed, String username) {

        response.setHeader(RESPONSE_HEADER_UNSUCCESSFUL_AUTHENTICATION, failed.getMessage());

        logger.warn("Unsuccessful authentication of: " + username + ", failure:" + failed.getMessage());

        userService.unsuccessfulAuthentication(username, failed);
    }

}
