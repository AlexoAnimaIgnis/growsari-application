package com.growsari.application.server.factory.security;

import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import org.springframework.data.domain.Example;

public interface UserExampleFactory {
    Example<GrowsariUser> createUser(FindUserRequestDTO requestDTO);
}
