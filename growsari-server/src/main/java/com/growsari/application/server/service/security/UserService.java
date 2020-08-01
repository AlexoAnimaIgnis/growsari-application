package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;

public interface UserService {

    GrowsariUserDetails authenticateUser();

    GrowsariUser saveUser(GrowsariUser growsariUser);

    GrowsariUser getUser(String loginName);

    void unsuccessfulAuthentication(String name, Throwable cause);
}
