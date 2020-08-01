package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@ManagedResource(objectName = "com.growsari.application:type=service,name=userServiceImpl",
        description = "User service")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    @Override
    public GrowsariUser authenticateUser() {
        return null;
    }

    @Override
    public GrowsariUser saveUser(GrowsariUser growsariUser) {
        return null;
    }

    @Override
    public GrowsariUser getUser(String id) {
        return null;
    }

    @Override
    public void unsuccessfulAuthentication(String name, Throwable cause) {

    }
}
