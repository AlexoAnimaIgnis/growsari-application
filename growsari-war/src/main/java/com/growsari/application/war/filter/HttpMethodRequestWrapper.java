package com.growsari.application.war.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
    private final String httpMethod;

    public HttpMethodRequestWrapper(HttpServletRequest request, String httpMethod) {
        super(request);
        this.httpMethod = httpMethod;
    }

    @Override
    public String getMethod() {
        return this.httpMethod;
    }
}
