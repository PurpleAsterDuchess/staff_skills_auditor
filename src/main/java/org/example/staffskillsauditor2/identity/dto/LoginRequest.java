package org.example.staffskillsauditor2.identity.dto;

public record LoginRequest(
        String emailOrUsername,
        String password
) {
   // add validation
}
