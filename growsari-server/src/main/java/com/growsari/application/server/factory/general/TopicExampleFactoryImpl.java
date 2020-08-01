package com.growsari.application.server.factory.general;

import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class TopicExampleFactoryImpl implements TopicExampleFactory {
    @Override
    public Example<Topic> createTopic(FindTopicRequestDTO requestDTO) {

        Topic topic = new Topic();

        topic.setSubject(requestDTO.getSubject());
        topic.setDescription(requestDTO.getDescription());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(topic, exampleMatcher);
    }
}
