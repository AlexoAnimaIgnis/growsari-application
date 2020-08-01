package com.growsari.application.server.dao.security;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.GrowsariUser;

/**
 * @author alexander.ballester
 */
public interface UserCustomRepository {
    PageableResponseDTO<GrowsariUser> findUser(FindUserRequestDTO requestDTO);
}
