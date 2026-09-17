package org.example.staffskillsauditor2.skills.ui.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateSkillCommand Unit Tests")
class CreateSkillCommandUnitTests {

    @Test
    @DisplayName("Should create command with all supplied values")
    void shouldCreateCommandWithAllValues() {
        CreateSkillCommand command = new CreateSkillCommand(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming"
        );

        assertEquals("SKILL001", command.id());
        assertEquals("Java", command.name());
        assertEquals(
                "Java programming language",
                command.description()
        );
        assertEquals("Programming", command.category());
    }

    @Test
    @DisplayName("Should return correct skill ID")
    void shouldReturnCorrectSkillId() {
        CreateSkillCommand command = createCommand();

        assertEquals("SKILL001", command.id());
    }

    @Test
    @DisplayName("Should return correct skill name")
    void shouldReturnCorrectSkillName() {
        CreateSkillCommand command = createCommand();

        assertEquals("Java", command.name());
    }

    @Test
    @DisplayName("Should return correct description")
    void shouldReturnCorrectDescription() {
        CreateSkillCommand command = createCommand();

        assertEquals(
                "Java programming language",
                command.description()
        );
    }

    @Test
    @DisplayName("Should return correct category")
    void shouldReturnCorrectCategory() {
        CreateSkillCommand command = createCommand();

        assertEquals("Programming", command.category());
    }

    @Test
    @DisplayName("Should consider commands with the same values equal")
    void shouldConsiderCommandsWithSameValuesEqual() {
        CreateSkillCommand first = createCommand();
        CreateSkillCommand second = createCommand();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("Should not consider commands with different values equal")
    void shouldNotConsiderDifferentCommandsEqual() {
        CreateSkillCommand first = createCommand();

        CreateSkillCommand second = new CreateSkillCommand(
                "SKILL002",
                "Python",
                "Python programming language",
                "Programming"
        );

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Should distinguish commands with different skill IDs")
    void shouldDistinguishDifferentSkillIds() {
        CreateSkillCommand first = createCommand();

        CreateSkillCommand second = new CreateSkillCommand(
                "SKILL002",
                "Java",
                "Java programming language",
                "Programming"
        );

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Should distinguish commands with different categories")
    void shouldDistinguishDifferentCategories() {
        CreateSkillCommand first = createCommand();

        CreateSkillCommand second = new CreateSkillCommand(
                "SKILL001",
                "Java",
                "Java programming language",
                "Software Development"
        );

        assertNotEquals(first, second);
    }

    private CreateSkillCommand createCommand() {
        return new CreateSkillCommand(
                "SKILL001",
                "Java",
                "Java programming language",
                "Programming"
        );
    }
}