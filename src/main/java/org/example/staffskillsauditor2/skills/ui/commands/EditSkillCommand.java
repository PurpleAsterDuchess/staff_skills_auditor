package org.example.staffskillsauditor2.skills.ui.commands;

public record EditSkillCommand(
        int skillLevel,
        String notes
) {
    public EditSkillCommand {
        if (skillLevel < 1 || skillLevel > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
    }
}
