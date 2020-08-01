package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.dao.general.GrowsariTopicRepository;
import com.growsari.application.server.factory.PageRequestFactory;
import com.growsari.application.server.factory.general.TopicExampleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Autowired
    private PageRequestFactory pageRequestFactory;
    @Autowired
    private GrowsariTopicRepository topicRepository;
    @Autowired
    private TopicExampleFactory topicExampleFactory;

    @Override
    public PageableResponseDTO<Topic> findTopics(FindTopicRequestDTO findTopicRequestDTO) {
        List<Topic> topicList = new ArrayList<>();
        PageRequest pageRequest = pageRequestFactory.create(findTopicRequestDTO);
        Page<Topic> result = topicRepository.findAll(
                topicExampleFactory.createTopic(findTopicRequestDTO), pageRequest
        );
        return new PageableResponseDTO<>(result.getTotalElements(), result.getContent());
    }

    @Override
    public Topic createTopic(Topic topic) {
        if (!isTopicUnique(topic.getSubject())) {
            throw new RuntimeException("Topic: " + topic.getSubject() + " already exists.");
        }
        topic.setModificationId(0);
        return topicRepository.saveAndFlush(topic);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        Topic oldTopic = topicRepository.getOne(topic.getId());
        Topic newTopic = cloneTopic(topic);

        if (oldTopic.getModificationId() == null) {
            throw new IllegalStateException("modificationId cannot be null");
        }

        newTopic.setModificationId(oldTopic.getModificationId() + 1);
        return topicRepository.saveAndFlush(newTopic);
    }

    @Override
    public Topic getTopic(String id) {
        return topicRepository.getOne(id);
    }

    @Override
    public void deleteTopic(String id) {
        Topic oldTopic = topicRepository.getOne(id);
        Topic newTopic = cloneTopic(oldTopic);

        /**
         * To Do
         * set boolean value for Deleted_at or isDeleted
         */
        newTopic.setModificationId(oldTopic.getModificationId() + 1);
        topicRepository.saveAndFlush(newTopic);
    }

    private boolean isTopicUnique(String subject) {
        return topicRepository.countBySubject(subject) < 1;
    }

    private Topic cloneTopic(Topic topic) {
        Topic newTopic = new Topic();
        Topic oldTopic = topicRepository.getOne(topic.getId());

        newTopic.setDescription(oldTopic.getDescription());
        newTopic.setSubject(oldTopic.getSubject());
        return newTopic;
    }
}
