package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;

public interface GrowsariUserDetailsHolder {
    GrowsariUserDetails getGrowsariUserDetails();

    String getCurrentLogin();
}
