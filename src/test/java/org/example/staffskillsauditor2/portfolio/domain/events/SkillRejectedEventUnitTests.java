package org.example.staffskillsauditor2.portfolio.domain.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SkillRejectedEvent Unit Tests")
class SkillRejectedEventUnitTests {

    @Test
    void shouldCreateEvent() {
        LocalDateTime rejectedOn =
                LocalDateTime.of(2026, 9, 17, 11, 30);

        SkillRejectedEvent event =
                new SkillRejectedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001",
                        "manager1",
                        rejectedOn
                );

        assertEquals("PORTFOLIO001", event.getPortfolioId() );
        assertEquals("STAFF001", event.getStaffId());
        assertEquals("SKILL001", event.getSkillId());
        assertEquals("manager1", event.getRejectedBy());
        assertEquals(rejectedOn, event.getRejectedOn());
    }

    @Test
    void shouldCreateEventWithoutId() {
        LocalDateTime rejectedOn =
                LocalDateTime.of(2026, 9, 17, 11, 30);

        SkillRejectedEvent event =
                new SkillRejectedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001",
                        "manager1",
                        rejectedOn
                );

        assertNull(event.getId());
        assertEquals("STAFF001", event.getStaffId());
        assertEquals("SKILL001", event.getSkillId());
        assertEquals("manager1", event.getRejectedBy());
        assertEquals(rejectedOn, event.getRejectedOn());
    }

    @Test
    void shouldAllowNullValues() {
        SkillRejectedEvent event =
                new SkillRejectedEvent(
                        null,
                        null,
                        null,
                        null,
                        null
                );

        assertNull(event.getId());
        assertNull(event.getStaffId());
        assertNull(event.getSkillId());
        assertNull(event.getRejectedBy());
        assertNull(event.getRejectedOn());
    }

    @Test
    void shouldCreateEventWithNewIdUsingWithId() {
        LocalDateTime rejectedOn =
                LocalDateTime.of(2026, 9, 17, 11, 30);

        SkillRejectedEvent original =
                new SkillRejectedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001",
                        "manager1",
                        rejectedOn
                );

        SkillRejectedEvent updated =
                (SkillRejectedEvent) original.withId(200L);

        assertEquals(200L, updated.getId());
        assertEquals(
                "STAFF001",
                updated.getStaffId()
        );
        assertEquals(
                "SKILL001",
                updated.getSkillId()
        );
        assertEquals(
                "manager1",
                updated.getRejectedBy()
        );
        assertEquals(
                rejectedOn,
                updated.getRejectedOn()
        );

        assertNull(original.getId());
    }
}