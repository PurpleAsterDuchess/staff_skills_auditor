package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.events.CreateStaffMember;
import java.time.LocalDate;

public class StaffMember extends AggregateRoot<StaffMember> {
    private final String firstName;
    private final String surname;
    private final String email;
    private final LocalDate hireDate;
    private final String department;

    private StaffMember(Identity<StaffMember> id, String firstName, String surname, String email, LocalDate hireDate, String department) {
        super(id);
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.hireDate = hireDate;
        this.department = department;
    }

    public static StaffMember staffMemberOf(Identity<StaffMember> id, String firstName, String surname, String email, LocalDate hireDate, String department) {
        return new StaffMember(id, firstName, surname, email, hireDate, department);
    }

    public static StaffMember staffMemberOfWithEvent(Identity<StaffMember> id, String firstName, String surname, String email, String department) {
        LocalDate hireDate = LocalDate.now();
        StaffMember staff = new StaffMember(id, firstName, surname, email, hireDate, department);

        staff.addDomainEvent(new CreateStaffMember(id.id(), firstName, surname, email, hireDate, department));
        return staff;
    }

    public String firstName() { return firstName; }
    public String surname() { return surname; }
    public String email() { return email; }
    public LocalDate hireDate() { return hireDate; }
    public String department() { return department; }
}