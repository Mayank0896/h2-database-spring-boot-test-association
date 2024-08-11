package com.application.memdb.api;

import lombok.Data;

@Data
public class APIResponse<T> {
    private Status status;
    private String message;
    private T body;

    public APIResponse(Status status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
