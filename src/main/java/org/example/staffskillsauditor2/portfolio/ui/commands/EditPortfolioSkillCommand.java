package org.example.staffskillsauditor2.portfolio.ui.commands;

public record EditPortfolioSkillCommand(
        int skillLevel,
        String notes
) {
    public EditPortfolioSkillCommand {
        if (skillLevel < 1 || skillLevel > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
    }
}
