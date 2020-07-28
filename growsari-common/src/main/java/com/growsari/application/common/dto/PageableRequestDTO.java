package com.growsari.application.common.dto;

import javax.validation.constraints.Min;

/**
 * @author alexander.ballester
 */
public class PageableRequestDTO {
    private static final String PAGE_PROP = "page";
    private static final String SIZE_PROP = "size";
    @Min(0)
    private int page;
    @Min(1)
    private int size;

    public PageableRequestDTO(){

    }

    public PageableRequestDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
