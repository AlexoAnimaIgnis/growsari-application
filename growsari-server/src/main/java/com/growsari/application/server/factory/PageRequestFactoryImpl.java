package com.growsari.application.server.factory;

import com.growsari.application.common.dto.PageableRequestDTO;
import org.springframework.data.domain.PageRequest;

public class PageRequestFactoryImpl implements PageRequestFactory{
    @Override
    public PageRequest create(PageableRequestDTO requestDTO) {
        return PageRequest.of(requestDTO.getPage(), requestDTO.getSize());
    }
}
