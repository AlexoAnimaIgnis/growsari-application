package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import org.springframework.stereotype.Service;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Override
    public PageableResponseDTO<Topic> findTopics(FindTopicRequestDTO findTopicRequestDTO) {
        return null;
    }

    @Override
    public Topic createTopic(Topic topic) {
        return null;
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return null;
    }

    @Override
    public Topic getTopic(String id) {
        return null;
    }

    @Override
    public void deleteTopic(String id) {

    }
}
