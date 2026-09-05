package org.example.staffskillsauditor2.skills.ui.commands;

import java.util.Objects;

public record RegisterStaffMemberCommand(
        String firstName,
        String surname,
        String email,
        String department,
        String roleName,
        String jobLevel,
        String employmentType,
        String employmentStatus
) {
    public RegisterStaffMemberCommand {
        Objects.requireNonNull(firstName, "First name is required");
        Objects.requireNonNull(surname, "Surname is required");
        Objects.requireNonNull(email, "Email is required");
        Objects.requireNonNull(department, "Department is required");
        Objects.requireNonNull(roleName, "Role name is required");
        Objects.requireNonNull(jobLevel, "Job level is required");
        Objects.requireNonNull(employmentType, "Employment type is required");
        Objects.requireNonNull(employmentStatus, "Employment status is required");

        if (firstName.isBlank()) throw new IllegalArgumentException("First name cannot be blank");
        if (surname.isBlank()) throw new IllegalArgumentException("Surname cannot be blank");
        if (email.isBlank()) throw new IllegalArgumentException("Email cannot be blank");
        if (department.isBlank()) throw new IllegalArgumentException("Department cannot be blank");
        if (roleName.isBlank()) throw new IllegalArgumentException("Role name cannot be blank");
        if (jobLevel.isBlank()) throw new IllegalArgumentException("Job level cannot be blank");
        if (employmentType.isBlank()) throw new IllegalArgumentException("Employment type cannot be blank");
        if (employmentStatus.isBlank()) throw new IllegalArgumentException("Employment status cannot be blank");
    }
}