package com.growsari.application.server.factory;

import com.growsari.application.common.dto.PageableRequestDTO;
import org.springframework.data.domain.PageRequest;

public interface PageRequestFactory {
    PageRequest create(PageableRequestDTO requestDTO);
}
