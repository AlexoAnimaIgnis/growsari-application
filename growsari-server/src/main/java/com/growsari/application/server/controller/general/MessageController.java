package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import com.growsari.application.server.service.general.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sk.nociar.jpacloner.JpaCloner;

import javax.ws.rs.core.MediaType;

/**
 * @author alexander.ballester
 */
@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    /**
     * Item 8: Get all messages in a Topic
     *
     * @param id
     * @param findMessageRequestDTO
     * @return
     */
    @GetMapping(value = {"/topic/{id}/messages"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    PageableResponseDTO<Message> findMessages(@PathVariable String id, @RequestBody FindMessageRequestDTO findMessageRequestDTO) {
        findMessageRequestDTO.setTopicId(id);
        PageableResponseDTO<Message> result = messageService.findMessages(findMessageRequestDTO);
        return new PageableResponseDTO<>(result.getTotalRecords(), JpaCloner.clone(result.getResult()));
    }

    /**
     * Item 6: Create message in a topic
     * @param message
     * @return
     */
    @PostMapping(value = {"/topic/{id}/message"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message createMessage(@PathVariable String id, @RequestBody Message message) {
        Assert.notNull(id, "Id should not be null");
        Assert.notNull(message, "message object should not be null");

        return getMessage(messageService.createMessage(message, id).getId());
    }

    @PutMapping(value = {"/messages/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message updateMessage(@PathVariable String id, @RequestBody Message message) {
        return null;
    }

    @GetMapping(value = {"/topic/{id}/message"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message getMessage(@PathVariable String id) {
        return messageService.getMessage(id);
    }

    @DeleteMapping(value = {"/messages/{id}"}, produces = MediaType.APPLICATION_JSON)
    void deleteMessage() {
    }
}
