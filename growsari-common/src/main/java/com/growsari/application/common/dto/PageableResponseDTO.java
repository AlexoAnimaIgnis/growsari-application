package com.growsari.application.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author alexander.ballester
 */
public class PageableResponseDTO<T extends Serializable> {
    private static final String TOTAL_RECORDS_PROP = "totalRecords";
    private static final String RESULT_PROP = "result";
    private long totalRecords;
    private List<T> result;

    public PageableResponseDTO(long totalRecords, List<T> result) {
        this.totalRecords = totalRecords;
        this.result = result;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public List<T> getResult() {
        return result;
    }
}
