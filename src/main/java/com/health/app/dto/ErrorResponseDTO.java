package com.health.app.dto;

public class ErrorResponseDTO {

    private final int status;
    private final String message;

    public ErrorResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
