package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;

public record RejectSkillCommand(
        String rejectedBy
) {
    public RejectSkillCommand {
        Objects.requireNonNull(rejectedBy, "Rejecter ID is required");
        if (rejectedBy.isBlank()) {
            throw new IllegalArgumentException("Rejecter ID cannot be blank");
        }
    }
}