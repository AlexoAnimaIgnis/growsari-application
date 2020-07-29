package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.User;

public interface UserService {

    User authenticateUser();

    User saveUser(User user);

    User getUser(String id);

    void unsuccessfulAuthentication(String name, Throwable cause);
}
