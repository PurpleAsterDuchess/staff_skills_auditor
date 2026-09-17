package org.example.staffskillsauditor2.skills.ui.controllers;

import org.example.staffskillsauditor2.skills.ContextFacade;
import org.example.staffskillsauditor2.skills.application.dto.SkillDTO;
import org.example.staffskillsauditor2.skills.ui.commands.CreateSkillCommand;
import org.example.staffskillsauditor2.skills.ui.commands.UpdateSkillCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SkillController Unit Tests")
class SkillControllerUnitTests {

    @Mock
    private ContextFacade facade;

    @InjectMocks
    private SkillController controller;

    private SkillDTO skill;

    @BeforeEach
    void setUp() {
        skill = new SkillDTO(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming",
                true
        );
    }

    @Test
    @DisplayName("Should get skill by ID")
    void shouldGetSkillById() {
        when(facade.findSkillById("SKILL001"))
                .thenReturn(skill);

        SkillDTO result = controller.getSkillById("SKILL001");

        assertNotNull(result);
        assertEquals(skill, result);

        verify(facade).findSkillById("SKILL001");
    }

    @Test
    @DisplayName("Should get all skills")
    void shouldGetAllSkills() {
        List<SkillDTO> skills = List.of(
                skill,
                new SkillDTO(
                        "SKILL002",
                        "Python",
                        "Python programming language",
                        "Programming",
                        true
                )
        );

        when(facade.findAllSkills())
                .thenReturn(skills);

        Iterable<SkillDTO> result = controller.getAllSkills();

        assertNotNull(result);

        List<SkillDTO> resultList = (List<SkillDTO>) result;

        assertEquals(2, resultList.size());
        assertEquals(skill, resultList.getFirst());

        verify(facade).findAllSkills();
    }

    @Test
    @DisplayName("Should create a skill")
    void shouldCreateSkill() {
        CreateSkillCommand command = new CreateSkillCommand(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming"
        );

        controller.createSkill(command);

        verify(facade).createSkill(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming"
        );
    }

    @Test
    @DisplayName("Should deactivate a skill")
    void shouldDeactivateSkill() {
        controller.deactivateSkill("SKILL001");

        verify(facade).deactivateSkill("SKILL001");
    }

    @Test
    @DisplayName("Should activate a skill")
    void shouldActivateSkill() {
        controller.activateSkill("SKILL001");

        verify(facade).activateSkill("SKILL001");
    }

    @Test
    @DisplayName("Should update a skill")
    void shouldUpdateSkill() {
        UpdateSkillCommand command = new UpdateSkillCommand(
                "Advanced Java",
                "Advanced Java programming",
                "Programming"
        );

        controller.updateSkill(
                "SKILL001",
                command
        );

        verify(facade).updateSkill(
                "SKILL001",
                "Advanced Java",
                "Advanced Java programming",
                "Programming"
        );
    }

    @Test
    @DisplayName("Should return the exact skill returned by the facade")
    void shouldReturnExactSkillFromFacade() {
        when(facade.findSkillById("SKILL001"))
                .thenReturn(skill);

        SkillDTO result = controller.getSkillById("SKILL001");

        assertSame(skill, result);

        verify(facade).findSkillById("SKILL001");
    }

    @Test
    @DisplayName("Should return empty list when no skills exist")
    void shouldReturnEmptyListWhenNoSkillsExist() {
        when(facade.findAllSkills())
                .thenReturn(List.of());

        Iterable<SkillDTO> result = controller.getAllSkills();

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());

        verify(facade).findAllSkills();
    }

    @Test
    @DisplayName("Should not call other facade methods when getting a skill")
    void shouldOnlyCallFindSkillById() {
        when(facade.findSkillById("SKILL001"))
                .thenReturn(skill);

        controller.getSkillById("SKILL001");

        verify(facade).findSkillById("SKILL001");
        verifyNoMoreInteractions(facade);
    }
}