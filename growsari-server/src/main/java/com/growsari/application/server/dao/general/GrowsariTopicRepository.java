package com.growsari.application.server.dao.general;

import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface GrowsariTopicRepository extends GrowsariJpaRepository<Topic, String> {
    Topic findBySubject(String subject);

    Long countBySubject(String subject);
}
