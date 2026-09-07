package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.Skill;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.common.domain.Identity;
import java.util.Objects;


public class SkillJpaToDomainMapper {

    public static Skill map(SkillJpa jpa) {
        Objects.requireNonNull(jpa, "Jpa entity cannot be null");

        String skillStatus = jpa.isActive() ? "ACTIVE" : "INACTIVE";

        return Skill.reconstitute(
                Identity.of(jpa.getId()),
                jpa.getName(),
                jpa.getDescription(),
                jpa.getCategory(),
                skillStatus
        );
    }
}
