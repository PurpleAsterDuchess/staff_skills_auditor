package org.example.staffskillsauditor2.staff.application.mappers;

import org.example.staffskillsauditor2.staff.domain.StaffMember;
import org.example.staffskillsauditor2.staff.persistance.entities.StaffJpa;

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