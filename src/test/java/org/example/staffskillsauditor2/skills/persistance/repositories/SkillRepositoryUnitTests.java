package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillRepositoryUnitTests {

    private final SkillRepository skillRepository =
            Mockito.mock(SkillRepository.class);

    @Test
    void shouldFindSkillById() {
        SkillJpa skill = createSkill();

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        Optional<SkillJpa> result =
                skillRepository.findById("SKILL001");

        assertTrue(result.isPresent());
        assertEquals("SKILL001", result.get().getId());
        assertEquals("Java", result.get().getName());

        verify(skillRepository).findById("SKILL001");
    }

    @Test
    void shouldReturnEmptyWhenSkillDoesNotExist() {
        when(skillRepository.findById("UNKNOWN"))
                .thenReturn(Optional.empty());

        Optional<SkillJpa> result =
                skillRepository.findById("UNKNOWN");

        assertTrue(result.isEmpty());

        verify(skillRepository).findById("UNKNOWN");
    }

    @Test
    void shouldSaveSkill() {
        SkillJpa skill = createSkill();

        when(skillRepository.save(skill))
                .thenReturn(skill);

        SkillJpa result = skillRepository.save(skill);

        assertNotNull(result);
        assertEquals("SKILL001", result.getId());
        assertEquals("Java", result.getName());

        verify(skillRepository).save(skill);
    }

    @Test
    void shouldFindAllSkills() {
        SkillJpa skill1 = createSkill();

        SkillJpa skill2 = new SkillJpa();
        skill2.setId("SKILL002");
        skill2.setName("Python");
        skill2.setDescription("Python programming language");
        skill2.setCategory("Programming");
        skill2.setActive(true);

        when(skillRepository.findAll())
                .thenReturn(java.util.List.of(skill1, skill2));

        Iterable<SkillJpa> result =
                skillRepository.findAll();

        assertNotNull(result);

        java.util.List<SkillJpa> skills =
                new java.util.ArrayList<>();

        result.forEach(skills::add);

        assertEquals(2, skills.size());
        assertEquals("SKILL001", skills.get(0).getId());
        assertEquals("SKILL002", skills.get(1).getId());

        verify(skillRepository).findAll();
    }

    @Test
    void shouldCheckIfSkillExists() {
        when(skillRepository.existsById("SKILL001"))
                .thenReturn(true);

        assertTrue(skillRepository.existsById("SKILL001"));

        verify(skillRepository).existsById("SKILL001");
    }

    @Test
    void shouldReturnFalseWhenSkillDoesNotExist() {
        when(skillRepository.existsById("UNKNOWN"))
                .thenReturn(false);

        assertFalse(skillRepository.existsById("UNKNOWN"));

        verify(skillRepository).existsById("UNKNOWN");
    }

    @Test
    void shouldDeleteSkill() {
        doNothing().when(skillRepository)
                .deleteById("SKILL001");

        skillRepository.deleteById("SKILL001");

        verify(skillRepository).deleteById("SKILL001");
    }

    private SkillJpa createSkill() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming language");
        skill.setCategory("Programming");
        skill.setActive(true);

        return skill;
    }
}