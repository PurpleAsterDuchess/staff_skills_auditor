package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.Skill;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import java.util.Objects;

public class SkillToJpaMapper {

    public static void map(Skill domain, SkillJpa jpa) {
        Objects.requireNonNull(domain, "Domain skill cannot be null");
        Objects.requireNonNull(jpa, "Jpa entity cannot be null");

        jpa.setId(domain.id().id());
        jpa.setName(domain.name());
        jpa.setDescription(domain.description());
        jpa.setCategory(domain.category());

        jpa.setActive(domain.isActive());
    }
}
