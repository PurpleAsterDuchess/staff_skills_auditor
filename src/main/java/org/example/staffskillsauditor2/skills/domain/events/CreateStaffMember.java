package org.example.staffskillsauditor2.skills.domain.events;


import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CreateStaffMember {
    private final String staffId;
    private final String firstName;
    private final String surname;
    private final String email;
    private final LocalDate hireDate;
    private final String department;

    public CreateStaffMember(String staffId, String firstName, String surname,
                                   String email, LocalDate hireDate, String department) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.hireDate = hireDate;
        this.department = department;
    }
}
