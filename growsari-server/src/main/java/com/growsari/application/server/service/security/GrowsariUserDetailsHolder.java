package com.growsari.application.server.service.security;

public interface GrowsariUserDetailsHolder {
    GrowsariUserDetails getGrowsariUserDetails();

    String getCurrentLogin();
}
