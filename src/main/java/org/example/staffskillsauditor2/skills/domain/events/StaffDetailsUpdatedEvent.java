package org.example.staffskillsauditor2.skills.domain.events;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class StaffDetailsUpdatedEvent {
    private final String staffId;
    private final String oldDepartment;
    private final String newDepartment;
    private final LocalDate occurredOn;

    public StaffDetailsUpdatedEvent(String staffId, String oldDepartment, String newDepartment) {
        this.staffId = staffId;
        this.oldDepartment = oldDepartment;
        this.newDepartment = newDepartment;
        this.occurredOn = LocalDate.now();
    }
}
