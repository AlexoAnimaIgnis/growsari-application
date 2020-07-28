package com.growsari.application.common.dto.general;

import com.growsari.application.common.dto.PageableRequestDTO;

public class FindMessageRequestDTO extends PageableRequestDTO {
    private String message;

    public FindMessageRequestDTO() {}

    public FindMessageRequestDTO(int page, int size) {
        super(page, size);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
