package org.example.staffskillsauditor2.skills.application.handlers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.skills.application.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.SkillJpaToDomainMapper;
import org.example.staffskillsauditor2.skills.domain.Skill;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.example.staffskillsauditor2.skills.application.mappers.SkillToJpaMapper;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SkillCommandHandler {

    private final SkillRepository skillRepository;
    private final DomainEventManager domainEventManager;

    @Transactional
    public void createSkill(String id, String name, String description, String category) {
        boolean exists = skillRepository.existsById(id);
        if (exists) {
            throw new IllegalArgumentException("Skill with ID '" + id + "' already exists in the catalog.");
        }

        Skill skill = Skill.createNewSkill(Identity.of(id), name, description, category);

        SkillJpa jpaEntity = new SkillJpa();
        SkillToJpaMapper.map(skill, jpaEntity);
        skillRepository.save(jpaEntity);

        if (skill.domainEventsExist()) {
            domainEventManager.manageDomainEvents(this.getClass().getSimpleName(), skill.listOfDomainEvents());
            skill.clearDomainEvents();
        }
    }

    @Transactional
    public void deactivateSkill(String id) {
        SkillJpa jpaEntity = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));

        Skill skill = SkillJpaToDomainMapper.map(jpaEntity);

        skill.deactivateSkill();

        SkillToJpaMapper.map(skill, jpaEntity);
        skillRepository.save(jpaEntity);

        if (skill.domainEventsExist()) {
            domainEventManager.manageDomainEvents(this.getClass().getSimpleName(), skill.listOfDomainEvents());
            skill.clearDomainEvents();
        }
    }

    @Transactional
    public void activateSkill(String id) {
        SkillJpa jpaEntity = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));

        Skill skill = SkillJpaToDomainMapper.map(jpaEntity);

        skill.activateSkill();

        SkillToJpaMapper.map(skill, jpaEntity);
        skillRepository.save(jpaEntity);

        if (skill.domainEventsExist()) {
            domainEventManager.manageDomainEvents(this.getClass().getSimpleName(), skill.listOfDomainEvents());
            skill.clearDomainEvents();
        }
    }

    @Transactional
    public void updateSkill(String id, String name, String description, String category) {
        SkillJpa jpaEntity = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));

        Skill skill = SkillJpaToDomainMapper.map(jpaEntity);

        String finalName = name != null ? name.trim() : skill.name();
        String finalDescription = description != null ? description.trim() : skill.description();
        String finalCategory = category != null ? category.trim() : skill.category();

        skill.editSkill(finalName, finalDescription, finalCategory);

        SkillToJpaMapper.map(skill, jpaEntity);
        skillRepository.save(jpaEntity);
    }
}
