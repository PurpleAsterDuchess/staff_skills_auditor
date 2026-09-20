package org.example.staffskillsauditor2.staff.domain;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.staff.domain.events.StaffMemberCreatedEvent;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StaffMemberUnitTests {

    @Test
    void shouldCreateStaffMemberWithAllValues() {
        Identity<StaffMember> id = Identity.of("STAFF001");
        LocalDate hireDate = LocalDate.of(2025, 1, 15);

        StaffMember staff = StaffMember.staffMemberOf(
                id,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                hireDate,
                "IT"
        );

        assertNotNull(staff);
        assertEquals(id, staff.id());
        assertEquals("Jane", staff.firstName());
        assertEquals("Doe", staff.surname());
        assertEquals("jane.doe@example.com", staff.email());
        assertEquals(hireDate, staff.hireDate());
        assertEquals("IT", staff.department());
    }

    @Test
    void shouldCreateStaffMemberWithEvent() {
        Identity<StaffMember> id = Identity.of("STAFF001");

        StaffMember staff = StaffMember.staffMemberOfWithEvent(
                id,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT"
        );

        assertNotNull(staff);

        assertEquals("STAFF001", staff.id().id());
        assertEquals("Jane", staff.firstName());
        assertEquals("Doe", staff.surname());
        assertEquals("jane.doe@example.com", staff.email());
        assertEquals("IT", staff.department());

        assertNotNull(staff.hireDate());
    }

    @Test
    void shouldSetHireDateToCurrentDateWhenCreatingWithEvent() {
        LocalDate beforeCreation = LocalDate.now();

        StaffMember staff = StaffMember.staffMemberOfWithEvent(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT"
        );

        LocalDate afterCreation = LocalDate.now();

        assertFalse(staff.hireDate().isBefore(beforeCreation));
        assertFalse(staff.hireDate().isAfter(afterCreation));
    }

    @Test
    void shouldNotCreateDomainEventWhenUsingStaffMemberOf() {
        StaffMember staff = StaffMember.staffMemberOf(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                LocalDate.of(2025, 1, 15),
                "IT"
        );

        assertFalse(staff.domainEventsExist());
        assertTrue(staff.listOfDomainEvents().isEmpty());
    }

    @Test
    void shouldCreateDomainEventWhenUsingStaffMemberOfWithEvent() {
        StaffMember staff = StaffMember.staffMemberOfWithEvent(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT"
        );

        assertTrue(staff.domainEventsExist());
        assertEquals(1, staff.listOfDomainEvents().size());
    }

    @Test
    void shouldCreateCorrectStaffMemberCreatedEvent() {
        StaffMember staff = StaffMember.staffMemberOfWithEvent(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT"
        );

        assertInstanceOf(
                StaffMemberCreatedEvent.class,
                staff.listOfDomainEvents().get(0)
        );

        StaffMemberCreatedEvent event =
                (StaffMemberCreatedEvent) staff.listOfDomainEvents().get(0);

        assertEquals("STAFF001", event.getStaffId());
        assertEquals("Jane", event.getFirstName());
        assertEquals("Doe", event.getSurname());
        assertEquals("jane.doe@example.com", event.getEmail());
        assertEquals("IT", event.getDepartment());
        assertEquals(staff.hireDate(), event.getHireDate());
    }

    @Test
    void shouldClearDomainEvents() {
        StaffMember staff = StaffMember.staffMemberOfWithEvent(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "IT"
        );

        assertTrue(staff.domainEventsExist());

        staff.clearDomainEvents();

        assertFalse(staff.domainEventsExist());
        assertTrue(staff.listOfDomainEvents().isEmpty());
    }

    @Test
    void shouldExposeSameIdentityThatWasProvided() {
        Identity<StaffMember> id = Identity.of("STAFF001");

        StaffMember staff = StaffMember.staffMemberOf(
                id,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                LocalDate.of(2025, 1, 15),
                "IT"
        );

        assertSame(id, staff.id());
        assertEquals("STAFF001", staff.id().id());
    }

    @Test
    void shouldAllowDifferentStaffMembersWithDifferentIdentities() {
        StaffMember staff1 = StaffMember.staffMemberOf(
                Identity.of("STAFF001"),
                "Jane",
                "Doe",
                "jane.doe@example.com",
                LocalDate.of(2025, 1, 15),
                "IT"
        );

        StaffMember staff2 = StaffMember.staffMemberOf(
                Identity.of("STAFF002"),
                "John",
                "Smith",
                "john.smith@example.com",
                LocalDate.of(2025, 2, 1),
                "Finance"
        );

        assertNotEquals(staff1.id(), staff2.id());
        assertNotEquals(staff1.email(), staff2.email());
        assertNotEquals(staff1.firstName(), staff2.firstName());
    }
}