package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;

public record AllocateSkillCommand(
        String skillId,
        int skillLevel,
        String notes
) {
    public AllocateSkillCommand {
        Objects.requireNonNull(skillId, "Skill ID is required");
        if (skillId.isBlank()) {
            throw new IllegalArgumentException("Skill ID cannot be blank");
        }
        if (skillLevel < 1 || skillLevel > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
    }
}