package com.growsari.application.server.factory.general;

import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import org.springframework.data.domain.Example;

public interface TopicExampleFactory {
    Example<Topic> createTopic(FindTopicRequestDTO requestDTO);
}
