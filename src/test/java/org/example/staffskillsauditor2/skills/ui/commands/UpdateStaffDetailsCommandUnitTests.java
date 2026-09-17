package org.example.staffskillsauditor2.skills.ui.commands;

import org.example.staffskillsauditor2.staff.ui.commands.UpdateStaffDetailsCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateStaffDetailsCommandUnitTests {

    @Test
    void shouldCreateCommandWithAllValues() {
        UpdateStaffDetailsCommand command = createCommand();

        assertNotNull(command);
    }

    @Test
    void shouldReturnCorrectFirstName() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("Jane", command.firstName());
    }

    @Test
    void shouldReturnCorrectLastName() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("Doe", command.surname());
    }

    @Test
    void shouldReturnCorrectEmail() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("jane.doe@example.com", command.email());
    }

    @Test
    void shouldReturnCorrectDepartment() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("IT", command.department());
    }

    @Test
    void shouldReturnCorrectRole() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("STAFF", command.roleName());
    }

    @Test
    void shouldReturnCorrectJobLevel() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("JUNIOR", command.jobLevel());
    }

    @Test
    void shouldReturnCorrectEmploymentType() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("Full-time", command.employmentType());
    }

    @Test
    void shouldReturnCorrectStatus() {
        UpdateStaffDetailsCommand command = createCommand();

        assertEquals("ACTIVE", command.employmentStatus());
    }

    @Test
    void shouldBeEqualWhenCommandsContainTheSameValues() {
        UpdateStaffDetailsCommand command1 = createCommand();
        UpdateStaffDetailsCommand command2 = createCommand();

        assertEquals(command1, command2);
        assertEquals(command1.hashCode(), command2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenCommandsContainDifferentValues() {
        UpdateStaffDetailsCommand command1 = createCommand();

        UpdateStaffDetailsCommand command2 = new UpdateStaffDetailsCommand(
                "John",
                "Smith",
                "john.smith@example.com",
                "Finance",
                "STAFF",
                "SENIOR",
                "Full-time",
                "ACTIVE"
        );

        assertNotEquals(command1, command2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        UpdateStaffDetailsCommand command = createCommand();

        assertNotEquals(null, command);
    }

    @Test
    void shouldNotBeEqualToDifferentObjectType() {
        UpdateStaffDetailsCommand command = createCommand();

        assertNotEquals(command, "STAFF002");
    }

    private UpdateStaffDetailsCommand createCommand() {
        return new UpdateStaffDetailsCommand(
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT",
                "STAFF",
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );
    }
}