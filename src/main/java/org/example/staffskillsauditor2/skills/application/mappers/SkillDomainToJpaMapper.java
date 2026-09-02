package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.SkillAggregate;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import java.util.Objects;

public class SkillDomainToJpaMapper {

    public static SkillJpa map(SkillAggregate domain) {
        Objects.requireNonNull(domain, "Skill aggregate cannot be null");

        SkillJpa jpa = new SkillJpa();
        jpa.setId(domain.id().id());
        jpa.setName(domain.name());
        jpa.setDescription(domain.description());
        jpa.setCategory(domain.category());
        jpa.setActive(domain.isActive());

        return jpa;
    }
}