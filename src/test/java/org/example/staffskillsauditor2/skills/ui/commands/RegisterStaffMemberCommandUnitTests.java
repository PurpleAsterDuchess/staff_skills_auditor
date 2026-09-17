package org.example.staffskillsauditor2.skills.ui.commands;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RegisterStaffMemberCommandUnitTests {

    @Test
    void shouldCreateCommandWithAllValues() {
        RegisterStaffMemberCommand command = createCommand();

        assertNotNull(command);
    }

    @Test
    void shouldReturnCorrectFirstName() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("Jane", command.firstName());
    }

    @Test
    void shouldReturnCorrectSurname() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("Doe", command.surname());
    }

    @Test
    void shouldReturnCorrectEmail() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("jane.doe@example.com", command.email());
    }

    @Test
    void shouldReturnCorrectDepartment() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("Engineering", command.department());
    }

    @Test
    void shouldReturnCorrectRole() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("STAFF", command.roleName());
    }


    @Test
    void shouldReturnCorrectJobLevel() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("JUNIOR", command.jobLevel());
    }

    @Test
    void shouldReturnCorrectEmploymentType() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("Full-time", command.employmentType());
    }

    @Test
    void shouldReturnCorrectStatus() {
        RegisterStaffMemberCommand command = createCommand();

        assertEquals("ACTIVE", command.employmentStatus());
    }

    @Test
    void shouldBeEqualWhenCommandsContainTheSameValues() {
        RegisterStaffMemberCommand command1 = createCommand();
        RegisterStaffMemberCommand command2 = createCommand();

        assertEquals(command1, command2);
        assertEquals(command1.hashCode(), command2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenCommandsContainDifferentValues() {
        RegisterStaffMemberCommand command1 = createCommand();

        RegisterStaffMemberCommand command2 = new RegisterStaffMemberCommand(
                "John",
                "Smith",
                "john.smith@example.com",
                "Engineering",
                "STAFF",
                "JUNIOR",
                "ACTIVE",
                "STAFF"
        );

        assertNotEquals(command1, command2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        RegisterStaffMemberCommand command = createCommand();

        assertNotEquals(null, command);
    }

    @Test
    void shouldNotBeEqualToDifferentObjectType() {
        RegisterStaffMemberCommand command = createCommand();

        assertNotEquals(command, "STAFF002");
    }

    private RegisterStaffMemberCommand createCommand() {
        return new RegisterStaffMemberCommand(
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "Engineering",
                "STAFF",
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );
    }
}