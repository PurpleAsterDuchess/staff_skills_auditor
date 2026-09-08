package org.example.staffskillsauditor2.skills.application.handlers;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.skills.domain.StaffMember;
import org.example.staffskillsauditor2.skills.application.mappers.StaffDomainToJpaMapper;
import org.example.staffskillsauditor2.skills.domain.events.UpdateStaffDetailsEvent;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.StaffRepository;
import org.example.staffskillsauditor2.skills.ui.commands.UpdateStaffDetailsCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;


@Service
@AllArgsConstructor
public class StaffCommandHandler {
    private final StaffRepository staffRepository;
    private final DomainEventManager domainEventManager;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public String registerStaffMember(String firstName, String surname, String email, String department,
                                      String roleName, String jobLevel, String employmentType, String employmentStatus) {

        Identity<StaffMember> staffId = Identity.generateId();

        StaffMember newStaff = StaffMember.staffMemberOfWithEvent(
                staffId, firstName, surname, email, department
        );

        StaffJpa jpaEntity = StaffDomainToJpaMapper.map(newStaff);
        jpaEntity.setRoleName(roleName);
        jpaEntity.setRoleStartDate(LocalDate.now());
        jpaEntity.setJobLevel(jobLevel);
        jpaEntity.setEmploymentType(employmentType);
        jpaEntity.setEmploymentStatus(employmentStatus);

        staffRepository.save(jpaEntity);

        if (newStaff.domainEventsExist()) {
            domainEventManager.manageDomainEvents(this.getClass().getSimpleName(), newStaff.listOfDomainEvents());
            newStaff.clearDomainEvents();
        }

        return staffId.id();
    }

    @Transactional
    public void updateStaffDetails(String staffId, UpdateStaffDetailsCommand command) {
        LOG.info("Request received to patch staff member ID: {}", staffId);

        StaffJpa staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff member not found with ID: " + staffId));

        boolean departmentChanged = false;
        String oldDepartment = staff.getDepartment();

        if (command.firstName() != null) {
            staff.setFirstName(command.firstName().trim());
        }
        if (command.surname() != null) {
            staff.setSurname(command.surname().trim());
        }
        if (command.email() != null) {
            staff.setEmail(command.email().trim());
        }
        if (command.department() != null) {
            String newDept = command.department().trim();
            if (!newDept.equalsIgnoreCase(oldDepartment != null ? oldDepartment.trim() : "")) {
                staff.setDepartment(newDept);
                departmentChanged = true;
            }
        }
        if (command.roleName() != null) {
            staff.setRoleName(command.roleName().trim());
            staff.setRoleStartDate(LocalDate.now());
        }
        if (command.jobLevel() != null) {
            staff.setJobLevel(command.jobLevel().trim());
        }
        if (command.employmentType() != null) {
            staff.setEmploymentType(command.employmentType().trim());
        }
        if (command.employmentStatus() != null) {
            staff.setEmploymentStatus(command.employmentStatus().trim());
        }

        staffRepository.save(staff);
        LOG.info("Staff member updated successfully.");

        if (departmentChanged) {
            UpdateStaffDetailsEvent event = new UpdateStaffDetailsEvent(
                    staffId,
                    oldDepartment,
                    staff.getDepartment()
            );
            eventPublisher.publishEvent(event);
            LOG.info("Published updateStaffDetailsEvent for staff member ID: {}", staffId);
        }
    }
}
