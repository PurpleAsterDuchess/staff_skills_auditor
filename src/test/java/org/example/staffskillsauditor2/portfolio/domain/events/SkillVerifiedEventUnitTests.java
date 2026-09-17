package org.example.staffskillsauditor2.portfolio.domain.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SkillVerifiedEvent Unit Tests")
class SkillVerifiedEventUnitTests {

    @Test
    void shouldCreateEventWithoutId() {
        LocalDateTime verifiedOn =
                LocalDateTime.of(2026, 9, 17, 10, 30);

        SkillVerifiedEvent event =
                new SkillVerifiedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001",
                        "manager1",
                        verifiedOn
                );

        assertNull(event.getId());
        assertEquals("STAFF001", event.getStaffId());
        assertEquals("SKILL001", event.getSkillId());
        assertEquals("manager1", event.getVerifiedBy());
        assertEquals(verifiedOn, event.getVerifiedOn());
    }

    @Test
    void shouldCreateEventWithNullValues() {
        SkillVerifiedEvent event =
                new SkillVerifiedEvent(
                        null,
                        null,
                        null,
                        null,
                        null
                );

        assertNull(event.getId());
        assertNull(event.getStaffId());
        assertNull(event.getSkillId());
        assertNull(event.getVerifiedBy());
        assertNull(event.getVerifiedOn());
    }

    @Test
    void shouldCreateEventWithNewIdUsingWithId() {
        LocalDateTime verifiedOn =
                LocalDateTime.of(2026, 9, 17, 10, 30);

        SkillVerifiedEvent original =
                new SkillVerifiedEvent(
                        "PORTFOLIO001",
                        "STAFF001",
                        "SKILL001",
                        "manager1",
                        verifiedOn
                );

        SkillVerifiedEvent updated =
                (SkillVerifiedEvent) original.withId(200L);

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
                updated.getVerifiedBy()
        );
        assertEquals(
                verifiedOn,
                updated.getVerifiedOn()
        );

        assertNull(original.getId());
    }
}