package com.growsari.application.server.factory.security;

import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class UserExampleFactoryImpl implements UserExampleFactory {
    @Override
    public Example<GrowsariUser> createUser(FindUserRequestDTO requestDTO) {
        GrowsariUser growsariUser = new GrowsariUser();

        growsariUser.setName(requestDTO.getName());
        growsariUser.setEmail(requestDTO.getEmail());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(growsariUser, exampleMatcher);
    }
}
