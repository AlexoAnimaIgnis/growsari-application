package com.growsari.application.common.dto.security;

import com.growsari.application.common.dto.PageableRequestDTO;

/**
 * @author alexander.ballester
 */
public class FindUserRequestDTO extends PageableRequestDTO {
    private static final String NAME_PROP = "name";
    private static final String EMAIL_PROP = "email";

    private String name;
    private String email;

    public FindUserRequestDTO() {}

    public FindUserRequestDTO(int page, int size){
        super(page, size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
