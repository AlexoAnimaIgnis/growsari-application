package com.growsari.application.server.factory.general;

import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import org.springframework.data.domain.Example;

public interface MessageExampleFactory {
    Example<Message> createMessage(FindMessageRequestDTO findMessageRequestDTO);
}
