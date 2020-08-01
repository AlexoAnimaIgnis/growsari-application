package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.dao.security.GrowsariUserRepository;
import com.growsari.application.util.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@ManagedResource(objectName = "com.growsari.application:type=service,name=userServiceImpl",
        description = "User service")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    @Autowired
    private GrowsariUserDetailsHolder growsariUserDetailsHolder;
    @Autowired
    private GrowsariUserRepository growsariUserRepository;

    @Override
    public GrowsariUserDetails authenticateUser() {
        GrowsariUserDetails growsariUserDetails = growsariUserDetailsHolder.getGrowsariUserDetails();
        GrowsariUser growsariUser = growsariUserRepository.getById(growsariUserDetails.getId());

        return growsariUserDetails;
    }

    @Override
    public GrowsariUser saveUser(GrowsariUser growsariUser) {
        return null;
    }

    @Override
    public GrowsariUser getUser(String loginName) {
        return growsariUserRepository.findByName(loginName);
    }

    @Override
    @CacheEvict(value = SecurityConstants.SPRING_SECURITY_USER_CACHE, key = "#login")
    public void unsuccessfulAuthentication(String name, Throwable cause) {
        GrowsariUser growsariUser = growsariUserRepository.findByName(name);
    }
}
