package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.service.general.TopicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class TopicControllerTest {
    private static final String SUBJ_PROP = "SUBJECT1";
    private static final String DESC_PROP = "DESCRIPTION1";
    private static final String ID_PROP = "ID_PROP";

    @Mock
    TopicService topicService;
    @InjectMocks
    TopicController topicController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findTopics() {
        FindTopicRequestDTO requestDTO = new FindTopicRequestDTO();
        List<Topic> topicList = Arrays.asList(new Topic(), new Topic());
        PageableResponseDTO<Topic> responseDTO = new PageableResponseDTO<>(topicList.size(), topicList);

        Mockito.when(topicService.findTopics(requestDTO)).thenReturn(responseDTO);

        PageableResponseDTO<Topic> result = topicController.findTopics(requestDTO);

        Assert.assertEquals(responseDTO.getResult(), result.getResult());
        Assert.assertEquals(responseDTO.getTotalRecords(), result.getTotalRecords());
        Mockito.verify(topicService).findTopics(requestDTO);
    }

    @Test
    public void createTopic() {
        Topic topic = new Topic(SUBJ_PROP, DESC_PROP);
        topic.setId(ID_PROP);

        Mockito.when(topicService.getTopic(ID_PROP)).thenReturn(topic);
        Mockito.when(topicService.createTopic(topic)).thenReturn(topic);

        Topic result = topicController.createTopic(topic);

        Assert.assertEquals(topic, result);
    }

    @Test
    public void updateTopic() {
        Topic topic = new Topic(SUBJ_PROP, DESC_PROP);
        topic.setId(ID_PROP);

        Mockito.when(topicService.getTopic(ID_PROP)).thenReturn(topic);
        Mockito.when(topicService.updateTopic(topic)).thenReturn(topic);

        Topic result = topicController.updateTopic(ID_PROP, topic);

        Assert.assertEquals(topic, result);
        Assert.assertEquals(topic.getSubject(), result.getSubject());
        Assert.assertEquals(topic.getId(), result.getId());
    }

    @Test
    public void deleteTopic() {
        topicController.deleteTopic(ID_PROP);
        Mockito.verify(topicService).deleteTopic(ID_PROP);
    }
}
