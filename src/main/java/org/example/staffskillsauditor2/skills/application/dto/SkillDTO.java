package org.example.staffskillsauditor2.skills.application.dto;

public record SkillDTO(
        String id,
        String name,
        String description,
        String category,
        boolean active
) {
}
