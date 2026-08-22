package org.example.staffskillsauditor2.skills.application.dto;
import java.time.LocalDate;

public record StaffDTO(
    String id,
    String firstName,
    String surname,
    String email,
    LocalDate hireDate,
    String department,
    String lineManagerId,
    String roleName,
    LocalDate roleStartDate,
    String jobLevel,
    String employmentType,
    String employmentStatus
    ) {
}
