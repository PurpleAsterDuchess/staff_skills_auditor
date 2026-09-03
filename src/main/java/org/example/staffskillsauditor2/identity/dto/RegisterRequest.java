package org.example.staffskillsauditor2.identity.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String role
) {
    // add validation
}
