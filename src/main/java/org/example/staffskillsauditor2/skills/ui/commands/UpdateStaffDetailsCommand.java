package org.example.staffskillsauditor2.skills.ui.commands;

public record UpdateStaffDetailsCommand(
        String firstName,
        String surname,
        String email,
        String department,
        String roleName,
        String jobLevel,
        String employmentType,
        String employmentStatus
) {
    public UpdateStaffDetailsCommand {
        if (firstName != null && firstName.trim().isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank if provided");
        }
        if (surname != null && surname.trim().isBlank()) {
            throw new IllegalArgumentException("Surname cannot be blank if provided");
        }
        if (email != null && email.trim().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank if provided");
        }
        if (department != null && department.trim().isBlank()) {
            throw new IllegalArgumentException("Department cannot be blank if provided");
        }
        if (roleName != null && roleName.trim().isBlank()) {
            throw new IllegalArgumentException("Role name cannot be blank if provided");
        }
        if (jobLevel != null && jobLevel.trim().isBlank()) {
            throw new IllegalArgumentException("Job level cannot be blank if provided");
        }
        if (employmentType != null && employmentType.trim().isBlank()) {
            throw new IllegalArgumentException("Employment type cannot be blank if provided");
        }
        if (employmentStatus != null && employmentStatus.trim().isBlank()) {
            throw new IllegalArgumentException("Employment status cannot be blank if provided");
        }
    }
}
