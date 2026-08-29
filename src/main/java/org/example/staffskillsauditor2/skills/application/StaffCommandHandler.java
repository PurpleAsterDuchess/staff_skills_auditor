package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.domain.events.CreateStaffMember;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.StaffRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffCommandHandler {

    private final StaffRepository staffRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public String registerStaffMember(String firstName, String surname, String email,
                                      String department, String roleName, String jobLevel,
                                      String employmentType, String employmentStatus) {

        String staffId = UUID.randomUUID().toString();

        StaffJpa staff = new StaffJpa();
        staff.setId(staffId);
        staff.setFirstName(firstName);
        staff.setSurname(surname);
        staff.setEmail(email);
        staff.setHireDate(LocalDate.now());
        staff.setDepartment(department);
        staff.setRoleName(roleName);
        staff.setRoleStartDate(LocalDate.now());
        staff.setJobLevel(jobLevel);
        staff.setEmploymentType(employmentType);
        staff.setEmploymentStatus(employmentStatus);

        staffRepository.save(staff);

        CreateStaffMember event = new CreateStaffMember(
                staffId, firstName, surname, email, staff.getHireDate(), department
        );
        eventPublisher.publishEvent(event);

        return staffId;
    }
}