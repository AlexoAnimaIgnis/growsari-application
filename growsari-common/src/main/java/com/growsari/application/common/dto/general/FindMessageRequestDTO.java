package com.growsari.application.common.dto.general;

import com.growsari.application.common.dto.PageableRequestDTO;
import com.growsari.application.common.model.general.Topic;

public class FindMessageRequestDTO extends PageableRequestDTO {
    private String message;
    private Topic topic;
    private String topicId;

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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
