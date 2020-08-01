package com.growsari.application.server.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class GrowsariUserDetails extends User {
    private static final long serialVersionUID = -1;
    private static final String TO_STRING_PATTERN = "%s; details:%s; id:%s";

    private String details;
    private String id;

    public GrowsariUserDetails(String username, String password,
                               boolean enabled, boolean accountNonExpired,
                               boolean credentialsNonExpired, boolean accountNonLocked,
                               Collection<? extends GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GrowsariUserDetails{" +
                "details='" + details + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
