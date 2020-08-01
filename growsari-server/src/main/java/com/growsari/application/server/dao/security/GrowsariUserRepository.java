package com.growsari.application.server.dao.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface GrowsariUserRepository extends GrowsariJpaRepository<GrowsariUser, String>, UserCustomRepository {
    GrowsariUser findByName(String name);

    GrowsariUser getById(String id);
}
