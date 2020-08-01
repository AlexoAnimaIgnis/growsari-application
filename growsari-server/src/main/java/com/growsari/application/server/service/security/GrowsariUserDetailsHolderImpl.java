package com.growsari.application.server.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("growsariUserDetails")
public class GrowsariUserDetailsHolderImpl implements GrowsariUserDetailsHolder {
    @Override
    public GrowsariUserDetails getGrowsariUserDetails() {
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();

        return auth != null? (GrowsariUserDetails) auth.getPrincipal(): null;
    }

    @Override
    public String getCurrentLogin() {
        GrowsariUserDetails growsariUserDetails = getGrowsariUserDetails();
        return growsariUserDetails != null? growsariUserDetails.getUsername():null;
    }
}
