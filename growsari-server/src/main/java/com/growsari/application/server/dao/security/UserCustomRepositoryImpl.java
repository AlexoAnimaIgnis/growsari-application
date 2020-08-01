package com.growsari.application.server.dao.security;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.factory.PageRequestFactory;
import com.growsari.application.server.factory.security.UserExampleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserCustomRepositoryImpl implements UserCustomRepository{

    @Lazy
    @Autowired
    private GrowsariUserRepository growsariUserRepository;

    @Autowired
    private PageRequestFactory pageRequestFactory;

    @Autowired
    private UserExampleFactory userExampleFactory;

    @Override
    public PageableResponseDTO<GrowsariUser> findUser(FindUserRequestDTO requestDTO) {
        PageRequest pageRequest = pageRequestFactory.create(requestDTO);
        Page<GrowsariUser> result = growsariUserRepository.findAll(
                userExampleFactory.createUser(requestDTO), pageRequest
        );
        return new PageableResponseDTO<>(result.getTotalElements(), result.getContent());
    }
}
