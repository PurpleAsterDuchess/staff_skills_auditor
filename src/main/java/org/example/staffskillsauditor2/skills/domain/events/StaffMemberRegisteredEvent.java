package org.example.staffskillsauditor2.skills.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.RemoteEvent;
import org.example.staffskillsauditor2.common.events.Event;
import java.time.LocalDate;

@Getter
public class StaffMemberRegisteredEvent implements RemoteEvent {
    private final Long id;
    private final LocalDate occurredOn;
    private final String staffId;
    private final String firstName;
    private final String surname;
    private final String email;

    public StaffMemberRegisteredEvent(String staffId, String firstName, String surname, String email) {
        this(null, LocalDate.now(), staffId, firstName, surname, email);
    }

    public StaffMemberRegisteredEvent(Long id, LocalDate occurredOn, String staffId, String firstName, String surname, String email) {
        this.id = id;
        this.occurredOn = occurredOn;
        this.staffId = staffId;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Event withId(Long newId) {
        return new StaffMemberRegisteredEvent(newId, this.occurredOn, this.staffId, this.firstName, this.surname, this.email);
    }

    // Solves the compiler error!
    @Override
    public String routingKey() {
        return "staffRegisteredKey"; // Matches your rabbitmq.outbox.bindings configuration
    }

    // Implemented in case RemoteEvent also defines an exchange method:
    @Override
    public String exchange() {
        return "staff-management"; // Matches your rabbitmq.outbox.bindings configuration
    }
}