package org.example.staffskillsauditor2.skills.application.handlers;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.skills.domain.StaffMember;
import org.example.staffskillsauditor2.skills.application.mappers.StaffDomainToJpaMapper;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class StaffCommandHandler {
    private final StaffRepository staffRepository;
    private final DomainEventManager domainEventManager; // Injected instead of ApplicationEventPublisher

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
}
