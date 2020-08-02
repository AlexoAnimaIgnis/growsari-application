package com.growsari.application.server.factory.general;

import com.growsari.application.common.dto.general.FindMessageRequestDTO;
import com.growsari.application.common.model.general.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class MessageExampleFactoryImpl implements MessageExampleFactory {
    @Override
    public Example<Message> createMessage(FindMessageRequestDTO findMessageRequestDTO) {
        Message message = new Message();

        message.setTopic(findMessageRequestDTO.getTopic());
        message.setMessage(findMessageRequestDTO.getMessage());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(message, exampleMatcher);
    }
}
