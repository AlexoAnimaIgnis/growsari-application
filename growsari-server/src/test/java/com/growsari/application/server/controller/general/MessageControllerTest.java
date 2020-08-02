package com.growsari.application.server.controller.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.service.general.MessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class MessageControllerTest {
    private static final String SUBJ_PROP = "SUBJECT1";
    private static final String DESC_PROP = "DESCRIPTION1";
    private static final String ID_PROP = "ID_PROP";
    private static final String MESSAGE_PROP = "MESSAGE_PROP";

    @Mock
    private MessageService messageService;
    @InjectMocks
    private MessageController messageController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMessage() {
        Topic topic = new Topic(SUBJ_PROP, DESC_PROP);
        topic.setId(ID_PROP);

        Message message = new Message(MESSAGE_PROP, topic);
        message.setId(ID_PROP);

        Mockito.when(messageService.getMessage(ID_PROP)).thenReturn(message);
        Mockito.when(messageService.createMessage(message)).thenReturn(message);

        Message result = messageController.createMessage(topic.getId(), message);

        Assert.assertEquals(message, result);
    }

    @Test
    public void findMessages() {
        FindMessageRequestDTO requestDTO = new FindMessageRequestDTO();
        List<Message> messageList = Arrays.asList(new Message(), new Message());
        PageableResponseDTO<Message> responseDTO = new PageableResponseDTO<>(messageList.size(), messageList);

        Mockito.when(messageService.findMessages(requestDTO)).thenReturn(responseDTO);

        PageableResponseDTO<Message> result = messageController.findMessages(requestDTO);

        Assert.assertEquals(responseDTO.getResult(), result.getResult());
        Assert.assertEquals(responseDTO.getTotalRecords(), result.getTotalRecords());
        Mockito.verify(messageService).findMessages(requestDTO);
    }
}
