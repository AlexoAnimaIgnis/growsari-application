package com.growsari.application.common.dto.security;

import java.util.List;

public class AuthenticateResponseDTO {

    private String id;
    private String name;
    private List<String> authorities;
    private List<String> activities;

    public AuthenticateResponseDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthenticateResponseDTO(String login, List<String> authorities, List<String> activities, String id) {
        this.name = login;
        this.authorities = authorities;
        this.activities = activities;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public List<String> getActivities() {
        return activities;
    }
}
