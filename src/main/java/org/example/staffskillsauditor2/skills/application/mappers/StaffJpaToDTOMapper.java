package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;

import java.util.Objects;

public class StaffJpaToDTOMapper {
    public static StaffDTO toStaffDTO(StaffJpa staff) {
        Objects.requireNonNull(staff, "Staff JPA entity cannot be null");

        return new StaffDTO(

        );
    }
}
