package com.growsari.application.common.dto.security;

public class AuthenticateResponseDTO {

    private String id;
    private String name;

    public AuthenticateResponseDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
