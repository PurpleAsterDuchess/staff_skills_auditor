package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;

import java.util.Objects;

public class StaffJpaToDTOMapper {
    public static StaffDTO toStaffDTO(StaffJpa staff) {
        Objects.requireNonNull(staff, "Staff JPA entity cannot be null");

        return new StaffDTO(
                staff.getId(),
                staff.getFirst_name(),
                staff.getSurname(),
                staff.getEmail(),
                staff.getHire_date(),
                staff.getDepartment(),
                staff.getLine_manager_id(),
                staff.getCurrent_role(),
                staff.getRole_start_date(),
                staff.getJob_level(),
                staff.getEmployment_type(),
                staff.getEmployment_status()
        );
    }
}
