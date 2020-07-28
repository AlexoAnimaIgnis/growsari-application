package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindTopicRequestDTO;
import com.growsari.application.common.model.general.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * @author alexander.ballester
 */
@RestController
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @GetMapping(value = {"/topics"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    PageableResponseDTO<Topic> findTopics(@RequestBody FindTopicRequestDTO findTopicRequestDTO) {
        return null;
    }

    @PostMapping(value = {"/topics"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic createTopic(@RequestBody Topic topic) {
        return null;
    }

    @PutMapping(value = {"/topics/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        return null;
    }

    @GetMapping(value = {"/topics/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Topic getTopic(@PathVariable String id) {
        return null;
    }

    @DeleteMapping(value = {"/topics/{id}"}, produces = MediaType.APPLICATION_JSON)
    void deleteTopic() {
    }
}
