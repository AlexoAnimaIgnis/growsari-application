package com.growsari.application.server.service.general;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;

public interface MessageService {
    Message createMessage(Message message, String topicId);

    Message getMessage(String id);

    PageableResponseDTO<Message> findMessages(FindMessageRequestDTO findMessageRequestDTO);
}
