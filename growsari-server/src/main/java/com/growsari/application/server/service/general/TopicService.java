package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;

public interface TopicService {
    PageableResponseDTO<Topic> findTopics(FindTopicRequestDTO findTopicRequestDTO);

    Topic createTopic(Topic topic);

    Topic updateTopic(Topic topic, String topicId);

    Topic getTopic(String id);

    void deleteTopic(String id);
}
