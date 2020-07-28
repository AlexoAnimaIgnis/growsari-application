package com.growsari.application.server.dao.general;

import com.growsari.application.common.model.general.Message;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface AbstractMessageRepository extends GrowsariJpaRepository<Message, String> {
}
