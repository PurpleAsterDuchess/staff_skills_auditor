package org.example.staffskillsauditor2.portfolio.domain.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SkillUnverifiedEvent Unit Tests")
class SkillUnverifiedEventUnitTests {

    @Test
    void shouldCreateEvent() {
        SkillUnverifiedEvent event =
                new SkillUnverifiedEvent(
                        100L,
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001"
                );

        assertEquals(100L, event.getId());
        assertEquals("STAFF001", event.getStaffId());
        assertEquals("SKILL001", event.getSkillId());
    }

    @Test
    void shouldCreateEventWithoutId() {
        SkillUnverifiedEvent event =
                new SkillUnverifiedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001"
                );

        assertNull(event.getId());
        assertEquals("STAFF001", event.getStaffId());
        assertEquals("SKILL001", event.getSkillId());
    }

    @Test
    void shouldAllowNullValues() {
        SkillUnverifiedEvent event =
                new SkillUnverifiedEvent(
                        null,
                        null,
                        null
                );

        assertNull(event.getId());
        assertNull(event.getStaffId());
        assertNull(event.getSkillId());
    }

    @Test
    void shouldCreateEventWithNewIdUsingWithId() {
        SkillUnverifiedEvent original =
                new SkillUnverifiedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001"
                );

        SkillUnverifiedEvent updated =
                (SkillUnverifiedEvent) original.withId(200L);

        assertEquals(200L, updated.getId());
        assertEquals(
                "STAFF001",
                updated.getStaffId()
        );
        assertEquals(
                "SKILL001",
                updated.getSkillId()
        );

        assertNull(original.getId());
    }
}