package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;

public record VerifySkillCommand(
        String verifiedBy
) {
    public VerifySkillCommand {
        Objects.requireNonNull(verifiedBy, "Verifier's staff ID is required");
        if (verifiedBy.isBlank()) {
            throw new IllegalArgumentException("Verifier's staff ID cannot be blank");
        }
    }
}
