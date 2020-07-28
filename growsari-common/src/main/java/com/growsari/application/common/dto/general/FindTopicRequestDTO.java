package com.growsari.application.common.dto.general;

import com.growsari.application.common.dto.PageableRequestDTO;

public class FindTopicRequestDTO extends PageableRequestDTO  {

    private String subject;
    private String description;

    public FindTopicRequestDTO() {

    }

    public FindTopicRequestDTO(int page, int size) {
        super(page, size);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
