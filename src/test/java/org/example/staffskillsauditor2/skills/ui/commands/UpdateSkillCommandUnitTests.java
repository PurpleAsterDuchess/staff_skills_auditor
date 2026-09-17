package org.example.staffskillsauditor2.skills.ui.commands;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.Skill;
import org.example.staffskillsauditor2.skills.domain.exceptions.CannotBeBlankException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateSkillCommandUnitTests {

    @Test
    void shouldCreateCommandWithAllValues() {
        UpdateSkillCommand command = createCommand();

        assertNotNull(command);
    }

    @Test
    void shouldReturnCorrectName() {
        UpdateSkillCommand command = createCommand();

        assertEquals("Java Programming", command.name());
    }

    @Test
    void shouldReturnCorrectDescription() {
        UpdateSkillCommand command = createCommand();

        assertEquals(
                "Advanced Java programming skills",
                command.description()
        );
    }

    @Test
    void shouldReturnCorrectCategory() {
        UpdateSkillCommand command = createCommand();

        assertEquals("Programming", command.category());
    }

    @Test
    void shouldAllowNullValues() {
        UpdateSkillCommand command = new UpdateSkillCommand(
                null,
                null,
                null
        );

        assertNull(command.name());
        assertNull(command.description());
        assertNull(command.category());
    }

    @Test
    void shouldBeEqualWhenCommandsContainTheSameValues() {
        UpdateSkillCommand command1 = createCommand();
        UpdateSkillCommand command2 = createCommand();

        assertEquals(command1, command2);
        assertEquals(command1.hashCode(), command2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenCommandsContainDifferentValues() {
        UpdateSkillCommand command1 = createCommand();

        UpdateSkillCommand command2 = new UpdateSkillCommand(
                "Spring Boot",
                "Spring Boot framework skills",
                "Frameworks"
        );

        assertNotEquals(command1, command2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        UpdateSkillCommand command = createCommand();

        assertNotEquals(null, command);
    }

    @Test
    void shouldNotBeEqualToDifferentObjectType() {
        UpdateSkillCommand command = createCommand();

        assertNotEquals(command, "Java Programming");
    }

    @Test
    void shouldThrowCannotBeBlankExceptionWhenNameIsEmpty() {
        Throwable exception = assertThrows(
                CannotBeBlankException.class,
                () -> new UpdateSkillCommand(
                        "",
                        "Advanced Java programming skills",
                        "Programming"
                )
        );
        assertEquals("Skill name cannot be blank", exception.getMessage());

    }

    @Test
    void shouldThrowCannotBeBlankExceptionWhenCategoryIsEmpty() {
        Throwable exception = assertThrows(
                CannotBeBlankException.class,
                () -> new UpdateSkillCommand(
                        "Java Programming",
                        "Advanced Java programming skills",
                        ""
                )
        );
        assertEquals("Skill category cannot be blank", exception.getMessage());

    }

    private UpdateSkillCommand createCommand() {
        return new UpdateSkillCommand(
                "Java Programming",
                "Advanced Java programming skills",
                "Programming"
        );
    }
}