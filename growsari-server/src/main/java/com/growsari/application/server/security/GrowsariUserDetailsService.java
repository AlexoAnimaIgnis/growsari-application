package com.growsari.application.server.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.common.model.security.UserActivity;
import com.growsari.application.server.service.security.ActivityService;
import com.growsari.application.server.service.security.GrowsariUserDetails;
import com.growsari.application.server.util.security.ActivityGrantedAuthority;
import com.growsari.application.util.SecurityConstants;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GrowsariUserDetailsService implements UserDetailsService {

    private static final String GROWSARI_ROLE = "ROLE_GROWSARI";

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        GrowsariUser growsariUser = userService.getUser(login);

        if(growsariUser == null) {
            throw new UsernameNotFoundException("User " + login + " not found!");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.addAll(getGrantedAuthorities(growsariUser));
        GrowsariUserDetails growsariUserDetails = new GrowsariUserDetails(
                login, growsariUser.getPassword(), true, true,
                true, true, grantedAuthorityList
        );
        growsariUserDetails.setDetails(growsariUser.getName());
        growsariUserDetails.setId(growsariUser.getId());
        return growsariUserDetails;
    }

    private List<GrantedAuthority> getGrantedAuthorities(GrowsariUser growsariUser) {
        Set<UserActivity> activitySet = activityService.getActivities(growsariUser);
        List<GrantedAuthority> authorities;

        authorities = activitySet.stream().map(ActivityGrantedAuthority::new).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(GROWSARI_ROLE));

        return authorities;
    }
}
