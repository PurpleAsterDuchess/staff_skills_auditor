package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.application.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.SkillJpaToDTOMapper;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class SkillQueryHandler {
    private SkillRepository skillRepository;

    public SkillDTO findSkillById(String skillId) {
        Objects.requireNonNull(skillId, "Skill ID cannot be null");

        SkillJpa jpaEntity = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found for ID: " + skillId));

        return SkillJpaToDTOMapper.toSkillDTO(jpaEntity);
    }
}
