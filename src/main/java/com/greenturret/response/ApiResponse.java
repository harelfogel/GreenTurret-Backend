package com.greenturret.response;

public class ApiResponse {
    private EResponseStatus status;
    private String message;
    private Object data;

    public ApiResponse(EResponseStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public EResponseStatus getStatus() {
        return status;
    }

    public void setStatus(EResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

