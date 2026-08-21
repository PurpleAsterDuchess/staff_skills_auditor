package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.dto.StaffDTO;
import org.example.staffskillsauditor2.skills.application.exceptions.StaffNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.StaffJpaToDTOMapper;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.StaffRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import org.slf4j.Logger;

@AllArgsConstructor
@Service
public class StaffQueryHandler {
    private StaffRepository staffRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public StaffDTO findStaffById(String staffId) {
        Objects.requireNonNull(staffId, "Staff ID cannot be null");

        StaffJpa jpaEntity = staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found for ID: " + staffId));

        return StaffJpaToDTOMapper.toStaffDTO(jpaEntity);
    }
}

