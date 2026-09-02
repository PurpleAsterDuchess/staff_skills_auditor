package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.StaffMember;
import org.example.staffskillsauditor2.skills.persistance.entities.StaffJpa;

public class StaffDomainToJpaMapper {
    public static StaffJpa map(StaffMember domain) {
        StaffJpa jpa = new StaffJpa();
        jpa.setId(domain.id().id());
        jpa.setFirstName(domain.firstName());
        jpa.setSurname(domain.surname());
        jpa.setEmail(domain.email());
        jpa.setHireDate(domain.hireDate());
        jpa.setDepartment(domain.department());
        return jpa;
    }
}