package org.example.staffskillsauditor2.skills.application.handlers;

import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.domain.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillQueryHandlerUnitTests {

    @Mock
    private SkillRepository skillRepository;

    private SkillQueryHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SkillQueryHandler(skillRepository);
    }

    @Test
    void shouldFindSkillById() {
        SkillJpa skill = createSkillJpa();

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        SkillDTO result =
                handler.findSkillById("SKILL001");

        assertNotNull(result);
        assertEquals("SKILL001", result.id());
        assertEquals("Java", result.name());
        assertEquals(
                "Java programming",
                result.description()
        );
        assertEquals("Programming", result.category());
        assertTrue(result.active());

        verify(skillRepository).findById("SKILL001");
    }

    @Test
    void shouldThrowSkillNotFoundExceptionWhenSkillDoesNotExist() {
        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.empty());

        assertThrows(
                SkillNotFoundException.class,
                () -> handler.findSkillById("SKILL001")
        );

        verify(skillRepository).findById("SKILL001");
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSkillIdIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> handler.findSkillById(null)
        );

        verifyNoInteractions(skillRepository);
    }

    @Test
    void shouldFindAllSkills() {
        SkillJpa skill1 = createSkillJpa();

        SkillJpa skill2 = new SkillJpa();
        skill2.setId("SKILL002");
        skill2.setName("Spring Boot");
        skill2.setDescription("Spring Boot development");
        skill2.setCategory("Programming");
        skill2.setActive(true);

        when(skillRepository.findAll())
                .thenReturn(List.of(skill1, skill2));

        Iterable<SkillDTO> result =
                handler.findAllSkills();

        List<SkillDTO> skills =
                ((List<SkillDTO>) result);

        assertEquals(2, skills.size());

        assertEquals("SKILL001", skills.get(0).id());
        assertEquals("Java", skills.get(0).name());

        assertEquals("SKILL002", skills.get(1).id());
        assertEquals("Spring Boot", skills.get(1).name());

        verify(skillRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoSkills() {
        when(skillRepository.findAll())
                .thenReturn(List.of());

        Iterable<SkillDTO> result =
                handler.findAllSkills();

        List<SkillDTO> skills =
                ((List<SkillDTO>) result);

        assertTrue(skills.isEmpty());

        verify(skillRepository).findAll();
    }

    @Test
    void shouldReturnInactiveSkillsWhenFindingAllSkills() {
        SkillJpa skill = createSkillJpa();
        skill.setActive(false);

        when(skillRepository.findAll())
                .thenReturn(List.of(skill));

        Iterable<SkillDTO> result =
                handler.findAllSkills();

        List<SkillDTO> skills =
                ((List<SkillDTO>) result);

        assertEquals(1, skills.size());
        assertFalse(skills.getFirst().active());

        verify(skillRepository).findAll();
    }

    private SkillJpa createSkillJpa() {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(true);

        return skill;
    }
}