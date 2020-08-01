package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.service.general.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import sk.nociar.jpacloner.JpaCloner;

import javax.ws.rs.core.MediaType;

/**
 * @author alexander.ballester
 */
@RestController
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    /**
     * returns list of topics
     * @param findTopicRequestDTO
     * @return
     */
    @GetMapping(value = {"/topics"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    PageableResponseDTO<Topic> findTopics(@RequestBody FindTopicRequestDTO findTopicRequestDTO) {
        PageableResponseDTO<Topic> result = topicService.findTopics(findTopicRequestDTO);
        return new PageableResponseDTO<Topic>(result.getTotalRecords(), JpaCloner.clone(result.getResult()));
    }

    /**
     * Item 3: Create Topic
     * @param topic
     * @return
     */
    @PostMapping(value = {"/topic"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic createTopic(@RequestBody Topic topic) {
        Assert.notNull(topic, "Topic should not be null");

        logger.debug(topic.toString());

        return getTopic(topicService.createTopic(topic).getId());
    }

    /**
     * Item 4: Update Topic
     * @param id
     * @param topic
     * @return
     */
    @PatchMapping(value = {"/topic/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        Assert.notNull(topic, "Topic " + id + " cannot be null");

        logger.debug(topic.toString());

        return getTopic(topicService.updateTopic(topic).getId());
    }

    @GetMapping(value = {"/topics/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic getTopic(@PathVariable String id) {
        return topicService.getTopic(id);
    }

    /**
     * Item 5: Delete Topic
     * @param id
     */
    @DeleteMapping(value = {"/topics/{id}"}, produces = MediaType.APPLICATION_JSON)
    void deleteTopic(@PathVariable String id) {
        Assert.notNull(id, "ID should not be null");
        topicService.deleteTopic(id);
    }
}
