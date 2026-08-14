package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

import java.util.Objects;

public class SkillJpaToDTOMapper {
    public static SkillDTO toSkillDTO(SkillJpa skill) {
        Objects.requireNonNull(skill, "Skill JPA entity cannot be null");

        return new SkillDTO(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getCategory(),
                skill.isActive()
        );
    }
}