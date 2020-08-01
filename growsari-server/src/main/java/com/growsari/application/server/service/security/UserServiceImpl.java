package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.dao.security.GrowsariUserRepository;
import com.growsari.application.util.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public GrowsariUserDetails authenticateUser() {
        GrowsariUserDetails growsariUserDetails = growsariUserDetailsHolder.getGrowsariUserDetails();
        GrowsariUser growsariUser = growsariUserRepository.getById(growsariUserDetails.getId());

        return growsariUserDetails;
    }

    @Override
    public GrowsariUser saveUser(GrowsariUser growsariUser) {
        if (!isUniqueLogin(growsariUser.getName())) {
            throw new RuntimeException("User with name : " + growsariUser.getName() + " already exists.");
        }
        growsariUser.setPassword(passwordEncoder.encode(growsariUser.getPassword()));
        return growsariUserRepository.save(growsariUser);
    }

    @Override
    public boolean isUniqueLogin(String login) {
        return growsariUserRepository.countByName(login) < 1;
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
