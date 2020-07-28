package com.growsari.application.server.dao.security;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.User;

/**
 * @author alexander.ballester
 */
public interface UserCustomRepository {
    PageableResponseDTO<User> findUser(FindUserRequestDTO requestDTO);
}
