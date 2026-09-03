package org.example.staffskillsauditor2.identity.dto;

public record RegisterResponse(
        String uid,
        String email,
        String username,
        String message
) {
    // add validation
}
