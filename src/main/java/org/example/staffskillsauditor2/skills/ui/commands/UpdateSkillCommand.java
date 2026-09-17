package org.example.staffskillsauditor2.skills.ui.commands;

import org.example.staffskillsauditor2.skills.domain.exceptions.CannotBeBlankException;

public record UpdateSkillCommand(
        String name,
        String description,
        String category
) {
    public UpdateSkillCommand {
        if (name != null && name.isBlank()) {
            throw new CannotBeBlankException("Skill name cannot be blank");
        }
        if (category != null && category.isBlank()) {
            throw new CannotBeBlankException("Skill category cannot be blank");
        }
    }
}