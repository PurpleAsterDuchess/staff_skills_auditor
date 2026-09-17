package org.example.staffskillsauditor2.staff.application.mappers;

import org.example.staffskillsauditor2.staff.application.dto.StaffDTO;
import org.example.staffskillsauditor2.staff.persistance.entities.StaffJpa;

import java.util.Objects;

public class StaffJpaToDTOMapper {
    public static StaffDTO toStaffDTO(StaffJpa staff) {
        Objects.requireNonNull(staff, "Staff JPA entity cannot be null");

        return new StaffDTO(
                staff.getId(),
                staff.getFirstName(),
                staff.getSurname(),
                staff.getEmail(),
                staff.getHireDate(),
                staff.getDepartment(),
                staff.getLineManagerId(),
                staff.getRoleName(),
                staff.getRoleStartDate(),
                staff.getJobLevel(),
                staff.getEmploymentType(),
                staff.getEmploymentStatus()
        );
    }
}
