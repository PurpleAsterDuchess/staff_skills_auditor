package org.example.staffskillsauditor2.skills.application.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StaffDTO Unit Tests")
class StaffDTOUnitTests {

    @Test
    @DisplayName("Should create StaffDTO with all supplied values")
    void shouldCreateStaffDTOWithAllValues() {
        StaffDTO dto = new StaffDTO(
                "STAFF001",
                "John",
                "Smith",
                "john.smith@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );

        assertEquals("STAFF001", dto.id());
        assertEquals("John", dto.firstName());
        assertEquals("Smith", dto.surname());
        assertEquals(
                "john.smith@example.com",
                dto.email()
        );
        assertEquals("MANAGER002", dto.lineManagerId());
    }

    @Test
    @DisplayName("Should return correct staff ID")
    void shouldReturnCorrectStaffId() {
        StaffDTO dto = createStaffDTO();

        assertEquals("STAFF001", dto.id());
    }

    @Test
    @DisplayName("Should return correct first name")
    void shouldReturnCorrectFirstName() {
        StaffDTO dto = createStaffDTO();

        assertEquals("John", dto.firstName());
    }

    @Test
    @DisplayName("Should return correct last name")
    void shouldReturnCorrectLastName() {
        StaffDTO dto = createStaffDTO();

        assertEquals("Smith", dto.surname());
    }

    @Test
    @DisplayName("Should return correct email")
    void shouldReturnCorrectEmail() {
        StaffDTO dto = createStaffDTO();

        assertEquals(
                "john.smith@example.com",
                dto.email()
        );
    }

    @Test
    @DisplayName("Should return correct line manager ID")
    void shouldReturnCorrectLineManagerId() {
        StaffDTO dto = createStaffDTO();

        assertEquals("MANAGER002", dto.lineManagerId());
    }

    @Test
    @DisplayName("Should allow null line manager ID")
    void shouldAllowNullLineManagerId() {
        StaffDTO dto = new StaffDTO(
                "STAFF001",
                "John",
                "Smith",
                "john.smith@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
    null,
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );

        assertNull(dto.lineManagerId());
    }

    @Test
    @DisplayName("Should allow null values")
    void shouldAllowNullValues() {
        StaffDTO dto = new StaffDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null

        );

        assertNull(dto.id());
        assertNull(dto.firstName());
        assertNull(dto.surname());
        assertNull(dto.email());
        assertNull(dto.lineManagerId());
    }

    @Test
    @DisplayName("Should consider DTOs with the same values equal")
    void shouldConsiderDTOsWithSameValuesEqual() {
        StaffDTO first = createStaffDTO();
        StaffDTO second = createStaffDTO();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("Should not consider DTOs with different values equal")
    void shouldNotConsiderDifferentDTOsEqual() {
        StaffDTO first = createStaffDTO();

        StaffDTO second = new StaffDTO(
                "STAFF002",
                "Jane",
                "Doe",
                "jane.doe@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Should distinguish staff with different email addresses")
    void shouldDistinguishStaffWithDifferentEmailAddresses() {
        StaffDTO first = new StaffDTO(
                "STAFF001",
                "John",
                "Smith",
                "john.smith@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );

        StaffDTO second = new StaffDTO(
                "STAFF001",
                "John",
                "Smith",
                "different@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );

        assertNotEquals(first, second);
    }

    private StaffDTO createStaffDTO() {
        return new StaffDTO(
                "STAFF001",
                "John",
                "Smith",
                "john.smith@example.com",
                LocalDate.of(2022, 3, 15),
                "IT",
                "MANAGER002",
                "STAFF",
                LocalDate.of(2022, 3, 15),
                "JUNIOR",
                "Full-time",
                "ACTIVE"
        );
    }
}