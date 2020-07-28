package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
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
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @GetMapping(value = {"/messages"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    PageableResponseDTO<Message> findMessages(@RequestBody FindMessageRequestDTO findMessageRequestDTO) {
        return null;
    }

    @PostMapping(value = {"/messages"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message createMessage(@RequestBody Message message) {
        return null;
    }

    @PutMapping(value = {"/messages/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message updateMessage(@PathVariable String id, @RequestBody Message message) {
        return null;
    }

    @GetMapping(value = {"/messages/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    Message getMessage(@PathVariable String id) {
        return null;
    }

    @DeleteMapping(value = {"/messages/{id}"}, produces = MediaType.APPLICATION_JSON)
    void deleteMessage() {
    }
}
