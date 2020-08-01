package com.growsari.application.war.filter;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class HttpMethodOverrideFilter extends OncePerRequestFilter {
    private static final String X_HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override";
    private static final HashSet<String> SUPPORTED_HTTP_METHODS = new HashSet<>(Arrays.asList(
            HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.GET.name()
    ));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String currentMethod = request.getMethod();
        HttpServletRequest httpServletRequest = request;
        Object errorException = httpServletRequest.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);

        if(HttpMethod.POST.name().equals(currentMethod) && errorException == null) {
            String httpMethod = httpServletRequest.getHeader(X_HTTP_METHOD_OVERRIDE_HEADER);

            if(!httpMethod.isEmpty()) {
                String method = httpMethod.toUpperCase();

                if(SUPPORTED_HTTP_METHODS.contains(method)) {
                    request = new HttpMethodRequestWrapper(httpServletRequest, method);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
