package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;

public interface UserService {

    GrowsariUser authenticateUser();

    GrowsariUser saveUser(GrowsariUser growsariUser);

    GrowsariUser getUser(String id);

    void unsuccessfulAuthentication(String name, Throwable cause);
}
