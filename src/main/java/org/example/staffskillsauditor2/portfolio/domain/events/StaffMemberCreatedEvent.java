package org.example.staffskillsauditor2.portfolio.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.LocalEvent;
import org.example.staffskillsauditor2.common.events.Event;
import java.time.LocalDate;

@Getter
public class StaffMemberCreatedEvent implements LocalEvent {
    private final Long id;
    private final String staffId;
    private final String firstName;
    private final String surname;
    private final String email;
    private final LocalDate hireDate;
    private final String department;

    public StaffMemberCreatedEvent(String staffId, String firstName, String surname, String email, LocalDate hireDate, String department) {
        this(null, staffId, firstName, surname, email, hireDate, department);
    }

    public StaffMemberCreatedEvent(Long id, String staffId, String firstName, String surname, String email, LocalDate hireDate, String department) {
        this.id = id;
        this.staffId = staffId;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.hireDate = hireDate;
        this.department = department;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Event withId(Long id) {
        return new StaffMemberCreatedEvent(id, this.staffId, this.firstName, this.surname, this.email, this.hireDate, this.department);
    }
}