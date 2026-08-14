package org.example.staffskillsauditor2.skills.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "staff")
@Table(name = "staff_member")
public class StaffJpa {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "first_name", nullable = false, unique = true, length = 100)
    private String first_name;

    @Column(name = "surname", nullable = false, length = 500)
    private String surname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "hire_date", nullable = false, unique = true, length = 100)
    private String hire_date;

    @Column(name = "department", nullable = false, length = 500)
    private String department;

    @Column(name = "line_manager_id", nullable = false, length = 50)
    private String line_manager_id;

    @Column(name = "current_role", nullable = false, unique = true, length = 100)
    private String current_role;

    @Column(name = "role_start_date", nullable = false, length = 500)
    private String role_start_date;

    @Column(name = "job_level", nullable = false, length = 50)
    private String job_level;

    @Column(name = "employment_type", nullable = false, unique = true, length = 100)
    private String employment_type;

    @Column(name = "employment_status", nullable = false, length = 500)
    private String employment_status;

}
