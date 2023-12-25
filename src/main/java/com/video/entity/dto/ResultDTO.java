package com.video.entity.dto;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private Boolean result;

    private String message;

    private T data;
    public ResultDTO() {
    }

    public ResultDTO(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
