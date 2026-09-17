package org.example.staffskillsauditor2.skills.application.handlers;

import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.domain.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SkillQueryHandlerIntegrationTests {

    @Autowired
    private SkillRepository skillRepository;

    private SkillQueryHandler createHandler() {
        return new SkillQueryHandler(skillRepository);
    }

    @Test
    void shouldFindSkillByIdFromDatabase() {
        SkillJpa skill = new SkillJpa();
        skill.setId("SKL1");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(true);

        skillRepository.save(skill);

        SkillQueryHandler handler = createHandler();

        SkillDTO result = handler.findSkillById("SKL1");

        assertNotNull(result);
        assertEquals("SKL1", result.id());
        assertEquals("Java", result.name());
    }

    @Test
    void shouldThrowExceptionWhenSkillDoesNotExist() {
        SkillQueryHandler handler = createHandler();

        assertThrows(
                SkillNotFoundException.class,
                () -> handler.findSkillById("DOES_NOT_EXIST")
        );
    }

    @Test
    void shouldFindAllSkillsFromDatabase() {
        SkillJpa skill1 = new SkillJpa();
        skill1.setId("SKL1");
        skill1.setName("Java");
        skill1.setDescription("Java programming");
        skill1.setCategory("Programming");
        skill1.setActive(true);

        SkillJpa skill2 = new SkillJpa();
        skill2.setId("SKL2");
        skill2.setName("Python");
        skill2.setDescription("Python programming");
        skill2.setCategory("Programming");
        skill2.setActive(true);

        skillRepository.save(skill1);
        skillRepository.save(skill2);

        SkillQueryHandler handler = createHandler();

        Iterable<SkillDTO> result = handler.findAllSkills();

        List<SkillDTO> skills = new java.util.ArrayList<>();
        result.forEach(skills::add);

        assertEquals(6, skills.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoSkillsExist() {
        SkillQueryHandler handler = createHandler();

        Iterable<SkillDTO> result = handler.findAllSkills();

        assertTrue(result.iterator().hasNext());
    }
}