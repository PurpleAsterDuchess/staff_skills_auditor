package org.example.staffskillsauditor2.portfolio.ui.commands;

import java.util.Objects;

public record RejectPortfolioSkillCommand(
        String rejectedBy
) {
    public RejectPortfolioSkillCommand {
        Objects.requireNonNull(rejectedBy, "Rejecter ID is required");
        if (rejectedBy.isBlank()) {
            throw new IllegalArgumentException("Rejecter ID cannot be blank");
        }
    }
}