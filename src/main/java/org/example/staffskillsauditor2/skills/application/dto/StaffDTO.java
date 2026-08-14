package org.example.staffskillsauditor2.skills.application.dto;

public record StaffDTO(
    String id,
    String first_name,
    String surname,
    String email,
    String hire_date,
    String department,
    String line_manager_id,
    String current_role,
    String role_start_date,
    String job_level,
    String employment_type,
    String employment_status
    ) {
}
