package org.example.staffskillsauditor2.skills.application.handlers;

import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.portfolio.domain.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillCommandHandlerUnitTests {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DomainEventManager domainEventManager;

    private SkillCommandHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SkillCommandHandler(
                skillRepository,
                domainEventManager
        );
    }

    @Test
    void shouldCreateSkill() {
        when(skillRepository.existsById("SKILL001"))
                .thenReturn(false);

        handler.createSkill(
                "SKILL001",
                "Java",
                "Java programming",
                "Programming"
        );

        verify(skillRepository).existsById("SKILL001");
        verify(skillRepository).save(any(SkillJpa.class));
    }

    @Test
    void shouldSaveSkillWithCorrectDetailsWhenCreating() {
        when(skillRepository.existsById("SKILL001"))
                .thenReturn(false);

        ArgumentCaptor<SkillJpa> captor =
                ArgumentCaptor.forClass(SkillJpa.class);

        handler.createSkill(
                "SKILL001",
                "Java",
                "Java programming",
                "Programming"
        );

        verify(skillRepository).save(captor.capture());

        SkillJpa savedSkill = captor.getValue();

        assertEquals("SKILL001", savedSkill.getId());
        assertEquals("Java", savedSkill.getName());
        assertEquals("Java programming", savedSkill.getDescription());
        assertEquals("Programming", savedSkill.getCategory());
        assertTrue(savedSkill.isActive());
    }

    @Test
    void shouldThrowExceptionWhenCreatingDuplicateSkill() {
        when(skillRepository.existsById("SKILL001"))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> handler.createSkill(
                        "SKILL001",
                        "Java",
                        "Java programming",
                        "Programming"
                )
        );

        verify(skillRepository, never()).save(any());
        verifyNoInteractions(domainEventManager);
    }

    @Test
    void shouldNotSaveSkillWhenDuplicateIdExists() {
        when(skillRepository.existsById("SKILL001"))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> handler.createSkill(
                        "SKILL001",
                        "Java",
                        "Java programming",
                        "Programming"
                )
        );

        verify(skillRepository, never()).save(any(SkillJpa.class));
    }

    @Test
    void shouldDeactivateSkill() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.deactivateSkill("SKILL001");

        verify(skillRepository).findById("SKILL001");
        verify(skillRepository).save(skill);

        assertFalse(skill.isActive());
    }

    @Test
    void shouldThrowSkillNotFoundExceptionWhenDeactivatingMissingSkill() {
        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.empty());

        assertThrows(
                SkillNotFoundException.class,
                () -> handler.deactivateSkill("SKILL001")
        );

        verify(skillRepository, never()).save(any());
    }

    @Test
    void shouldActivateSkill() {
        SkillJpa skill = createSkillJpa(false);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.activateSkill("SKILL001");

        verify(skillRepository).findById("SKILL001");
        verify(skillRepository).save(skill);

        assertTrue(skill.isActive());
    }

    @Test
    void shouldThrowSkillNotFoundExceptionWhenActivatingMissingSkill() {
        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.empty());

        assertThrows(
                SkillNotFoundException.class,
                () -> handler.activateSkill("SKILL001")
        );

        verify(skillRepository, never()).save(any());
    }
    
    @Test
    void shouldUpdateSkill() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.updateSkill(
                "SKILL001",
                "Advanced Java",
                "Advanced Java programming",
                "Programming"
        );

        verify(skillRepository).findById("SKILL001");
        verify(skillRepository).save(skill);

        assertEquals("Advanced Java", skill.getName());
        assertEquals(
                "Advanced Java programming",
                skill.getDescription()
        );
        assertEquals("Programming", skill.getCategory());
    }

    @Test
    void shouldTrimValuesWhenUpdatingSkill() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.updateSkill(
                "SKILL001",
                "  Advanced Java  ",
                "  Advanced Java programming  ",
                "  Programming  "
        );

        assertEquals("Advanced Java", skill.getName());
        assertEquals(
                "Advanced Java programming",
                skill.getDescription()
        );
        assertEquals("Programming", skill.getCategory());

        verify(skillRepository).save(skill);
    }

    @Test
    void shouldKeepExistingNameWhenNameIsNull() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.updateSkill(
                "SKILL001",
                null,
                "Updated description",
                "Updated category"
        );

        assertEquals("Java", skill.getName());
        assertEquals(
                "Updated description",
                skill.getDescription()
        );
        assertEquals("Updated category", skill.getCategory());

        verify(skillRepository).save(skill);
    }

    @Test
    void shouldKeepExistingDescriptionWhenDescriptionIsNull() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.updateSkill(
                "SKILL001",
                "Updated Java",
                null,
                "Programming"
        );

        assertEquals("Updated Java", skill.getName());
        assertEquals("Java programming", skill.getDescription());
        assertEquals("Programming", skill.getCategory());

        verify(skillRepository).save(skill);
    }

    @Test
    void shouldKeepExistingCategoryWhenCategoryIsNull() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        handler.updateSkill(
                "SKILL001",
                "Updated Java",
                "Updated description",
                null
        );

        assertEquals("Updated Java", skill.getName());
        assertEquals("Updated description", skill.getDescription());
        assertEquals("Programming", skill.getCategory());

        verify(skillRepository).save(skill);
    }

    @Test
    void shouldThrowSkillNotFoundExceptionWhenUpdatingMissingSkill() {
        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.empty());

        assertThrows(
                SkillNotFoundException.class,
                () -> handler.updateSkill(
                        "SKILL001",
                        "Java",
                        "Java programming",
                        "Programming"
                )
        );

        verify(skillRepository, never()).save(any());
    }

    @Test
    void shouldThrowCannotBeBlankExceptionWhenUpdatingWithBlankName() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        assertThrows(
                RuntimeException.class,
                () -> handler.updateSkill(
                        "SKILL001",
                        "",
                        "Updated description",
                        "Programming"
                )
        );

        verify(skillRepository, never()).save(any());
    }

    @Test
    void shouldThrowCannotBeBlankExceptionWhenUpdatingWithBlankCategory() {
        SkillJpa skill = createSkillJpa(true);

        when(skillRepository.findById("SKILL001"))
                .thenReturn(Optional.of(skill));

        assertThrows(
                RuntimeException.class,
                () -> handler.updateSkill(
                        "SKILL001",
                        "Java",
                        "Updated description",
                        ""
                )
        );

        verify(skillRepository, never()).save(any());
    }

    // ---------------------------------------------------------
    // HELPER
    // ---------------------------------------------------------

    private SkillJpa createSkillJpa(boolean active) {
        SkillJpa skill = new SkillJpa();

        skill.setId("SKILL001");
        skill.setName("Java");
        skill.setDescription("Java programming");
        skill.setCategory("Programming");
        skill.setActive(active);

        return skill;
    }
}