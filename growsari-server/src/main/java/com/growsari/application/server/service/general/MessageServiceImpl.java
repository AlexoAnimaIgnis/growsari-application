package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import com.growsari.application.server.dao.general.GrowsariMessageRepository;
import com.growsari.application.server.factory.PageRequestFactory;
import com.growsari.application.server.factory.general.MessageExampleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private GrowsariMessageRepository growsariMessageRepository;
    @Autowired
    private PageRequestFactory pageRequestFactory;
    @Autowired
    private MessageExampleFactory messageExampleFactory;

    @Override
    public Message createMessage(Message message) {
        message.setModificationId(0);
        return growsariMessageRepository.saveOrUpdate(message);
    }

    @Override
    public Message getMessage(String id) {
        return growsariMessageRepository.getOne(id);
    }

    @Override
    public PageableResponseDTO<Message> findMessages(FindMessageRequestDTO findMessageRequestDTO) {
        List<Message> messages = new ArrayList<>();
        PageRequest pageRequest = pageRequestFactory.create(findMessageRequestDTO);
        Page<Message> result = growsariMessageRepository.findAll(
                messageExampleFactory.createMessage(findMessageRequestDTO), pageRequest
        );
        return new PageableResponseDTO<>(result.getTotalElements(), result.getContent());
    }
}
