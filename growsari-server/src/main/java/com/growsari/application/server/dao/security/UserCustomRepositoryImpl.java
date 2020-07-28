package com.growsari.application.server.dao.security;

import com.growsari.application.common.dto.PageableResponseDTO;
import com.growsari.application.common.dto.security.FindUserRequestDTO;
import com.growsari.application.common.model.security.User;
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
    private UserRepository userRepository;

    @Autowired
    private PageRequestFactory pageRequestFactory;

    @Autowired
    private UserExampleFactory userExampleFactory;

    @Override
    public PageableResponseDTO<User> findUser(FindUserRequestDTO requestDTO) {
        PageRequest pageRequest = pageRequestFactory.create(requestDTO);
        Page<User> result = userRepository.findAll(
                userExampleFactory.createUser(requestDTO), pageRequest
        );
        return new PageableResponseDTO<>(result.getTotalElements(), result.getContent());
    }
}
