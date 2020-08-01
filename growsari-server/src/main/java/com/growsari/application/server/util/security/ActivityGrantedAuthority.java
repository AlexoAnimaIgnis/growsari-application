package com.growsari.application.server.util.security;

import com.growsari.application.common.model.security.UserActivity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class ActivityGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private UserActivity userActivity;

    public ActivityGrantedAuthority(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

    @Override
    public String getAuthority() {
        return userActivity.toAuthority();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ActivityGrantedAuthority &&
                userActivity.equals(((ActivityGrantedAuthority) obj).userActivity);
    }

    @Override
    public int hashCode() {
        return this.userActivity.hashCode();
    }

    @Override
    public String toString() {
        return this.userActivity.toString();
    }

    public UserActivity getActivity() {
        return userActivity;
    }
}
