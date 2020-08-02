package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.dao.general.GrowsariTopicRepository;
import com.growsari.application.server.factory.PageRequestFactory;
import com.growsari.application.server.factory.general.TopicExampleFactory;
import com.growsari.application.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//        if (!isTopicUnique(topic.getSubject())) {
//            throw new RuntimeException("Topic: " + topic.getSubject() + " already exists.");
//        }
        topic.setModificationId(0);

        topic.setCreatedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
        topic.setUpdatedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
        return topicRepository.saveAndFlush(topic);
    }

    @Override
    public Topic updateTopic(Topic topic, String topicId) {
        Topic oldTopic = topicRepository.getOne(topic.getId());
        Topic newTopic = cloneTopic(topic);

        if (oldTopic.getModificationId() == null) {
            throw new IllegalStateException("modificationId cannot be null");
        }

        newTopic.setId(oldTopic.getId());
        newTopic.setModificationId(oldTopic.getModificationId() + 1);
        newTopic.setCreatedAt(oldTopic.getCreatedAt());
        newTopic.setUpdatedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
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

        newTopic.setModificationId(oldTopic.getModificationId() + 1);
        newTopic.setDeletedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
        topicRepository.saveAndFlush(newTopic);
    }

    private boolean isTopicUnique(String subject) {
        return topicRepository.countBySubject(subject) < 1;
    }

    private Topic cloneTopic(Topic topic) {
        Topic newTopic = new Topic();
        Topic oldTopic = topicRepository.getOne(topic.getId());

        newTopic.setId(topic.getId());
        newTopic.setDescription(topic.getDescription());
        newTopic.setSubject(topic.getSubject());
        newTopic.setCreatedBy(topic.getCreatedBy());
        newTopic.setUpdatedBy(topic.getUpdatedBy());
        newTopic.setCreatedAt(topic.getCreatedAt());
        newTopic.setUpdatedAt(topic.getUpdatedAt());
        return newTopic;
    }
}
