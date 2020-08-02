package com.growsari.application.server.dao.general;

import com.growsari.application.common.model.general.Message;
import com.growsari.application.common.model.general.Topic;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GrowsariMessageRepository extends GrowsariJpaRepository<Message, String> {
    List<Message> findByTopic(Topic topic);

    long countByTopic(Topic topic);

    Page<Message> findByTopic(Topic topic, Pageable pageable);
}
