package org.example.staffskillsauditor2.skills.ui.commands;

public record UpdateSkillCommand(
        String name,
        String description,
        String category
) {
    public UpdateSkillCommand {
        if (name != null && name.isBlank()) {
            throw new IllegalArgumentException("Skill name cannot be blank if provided");
        }
        if (category != null && category.isBlank()) {
            throw new IllegalArgumentException("Skill category cannot be blank if provided");
        }
    }
}