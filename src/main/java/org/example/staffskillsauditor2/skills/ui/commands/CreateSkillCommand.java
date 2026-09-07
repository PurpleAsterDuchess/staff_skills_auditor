package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;


public record CreateSkillCommand(
        String id,
        String name,
        String description,
        String category
) {
    public CreateSkillCommand {
        Objects.requireNonNull(id, "Skill ID is required");
        Objects.requireNonNull(name, "Skill name is required");
        Objects.requireNonNull(category, "Skill category is required");

        if (id.isBlank()) {
            throw new IllegalArgumentException("Skill ID cannot be blank");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Skill name cannot be blank");
        }
        if (category.isBlank()) {
            throw new IllegalArgumentException("Skill category cannot be blank");
        }
    }
}
