package com.growsari.application.server.security;

import com.growsari.application.common.model.security.GrowsariUser;
import org.springframework.security.core.userdetails.User;
import com.growsari.application.server.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class GrowsariUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        GrowsariUser growsariUser = userService.getUser(login);

        if(growsariUser == null) {
            throw new UsernameNotFoundException("User " + login + " not found!");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        User validUser = new User(
                login, growsariUser.getPassword(), true, true, true, true, grantedAuthorityList
        );
        return validUser;
    }
}
