package com.growsari.application.server.dao.security;

import com.growsari.application.common.model.security.User;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface UserRepository extends GrowsariJpaRepository<User, String>, UserCustomRepository {
    User findByName(String name);

    User getById(String id);
}
