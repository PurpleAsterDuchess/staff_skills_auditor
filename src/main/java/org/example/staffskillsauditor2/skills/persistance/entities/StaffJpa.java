package org.example.staffskillsauditor2.skills.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity(name = "staff")
@Table(name = "staff_member")
@Getter
@Setter
@ToString
public class StaffJpa {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "first_name", nullable = false, unique = false, length = 100)
    private String firstName;

    @Column(name = "surname", nullable = false, length = 500)
    private String surname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "hire_date", nullable = false, unique = false, length = 100)
    private LocalDate hireDate;

    @Column(name = "department", nullable = false, length = 500)
    private String department;

    @Column(name = "line_manager_id", nullable = true, length = 50)
    private String lineManagerId;

    @Column(name = "role_name", nullable = false, unique = false, length = 100)
    private String roleName;

    @Column(name = "role_start_date", nullable = false, length = 500)
    private LocalDate roleStartDate;

    @Column(name = "job_level", nullable = false, length = 50)
    private String jobLevel;

    @Column(name = "employment_type", nullable = false, unique = false, length = 100)
    private String employmentType;

    @Column(name = "employment_status", nullable = false, length = 500)
    private String employmentStatus;

}
