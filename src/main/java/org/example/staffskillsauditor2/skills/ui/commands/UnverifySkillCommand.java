package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;

public record UnverifySkillCommand(
        String unverifiedBy
) {
    public UnverifySkillCommand {
        Objects.requireNonNull(unverifiedBy, "Unverifier ID is required");
        if (unverifiedBy.isBlank()) {
            throw new IllegalArgumentException("Unverifier ID cannot be blank");
        }
    }
}