package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.dao.general.GrowsariMessageRepository;
import com.growsari.application.server.dao.general.GrowsariTopicRepository;
import com.growsari.application.server.factory.PageRequestFactory;
import com.growsari.application.server.factory.general.MessageExampleFactory;
import com.growsari.application.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private GrowsariMessageRepository growsariMessageRepository;
    @Autowired
    private PageRequestFactory pageRequestFactory;
    @Autowired
    private MessageExampleFactory messageExampleFactory;
    @Autowired
    private GrowsariTopicRepository topicRepository;

    @Override
    public Message createMessage(Message message, String topicId) {
        Topic topic = topicRepository.getOne(topicId);

        message.setTopic(topic);
        message.setModificationId(0);
        message.setCreatedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
        message.setUpdatedAt(DateHelper.toLocalDateTime(
                LocalDateTime.now().toString(),
                DateTimeFormatter.ISO_DATE_TIME));
        return growsariMessageRepository.saveAndFlush(message);
    }

    @Override
    public Message getMessage(String id) {
        return growsariMessageRepository.getOne(id);
    }

    @Override
    public PageableResponseDTO<Message> findMessages(FindMessageRequestDTO findMessageRequestDTO) {
        findMessageRequestDTO.setTopic(topicRepository.getOne(findMessageRequestDTO.getTopicId()));

        PageRequest pageRequest = pageRequestFactory.create(findMessageRequestDTO);
        Page<Message> result = growsariMessageRepository.findByTopic(
                findMessageRequestDTO.getTopic(), pageRequest
        );
        return new PageableResponseDTO<>(result.getTotalElements(), result.getContent());
    }
}
