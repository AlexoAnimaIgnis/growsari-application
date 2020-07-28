package com.growsari.application.server.factory.security;

import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class UserExampleFactoryImpl implements UserExampleFactory {
    @Override
    public Example<User> createUser(FindUserRequestDTO requestDTO) {
        User user = new User();

        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(user, exampleMatcher);
    }
}
