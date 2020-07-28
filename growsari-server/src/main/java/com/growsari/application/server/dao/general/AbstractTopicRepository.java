package com.growsari.application.server.dao.general;

import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface AbstractTopicRepository extends GrowsariJpaRepository<Topic, String> {
}
