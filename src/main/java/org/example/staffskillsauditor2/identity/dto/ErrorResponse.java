package org.example.staffskillsauditor2.identity.dto;

public record ErrorResponse(
        String error,
        String message
) {
    public ErrorResponse(String error) {
        this(error, null);
    }
}
