package org.example.staffskillsauditor2.portfolio.domain.events;

import org.example.staffskillsauditor2.common.events.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SkillAllocatedEvent Unit Tests")
class SkillAllocatedEventUnitTests {

    private static final String PORTFOLIO_ID = "portfolio1";
    private static final String STAFF_ID = "staff1";
    private static final String SKILL_ID = "skill1";
    private static final int SKILL_LEVEL = 4;
    private static final String NOTES = "Java development experience";

    private SkillAllocatedEvent createEvent() {
        return new SkillAllocatedEvent(
                PORTFOLIO_ID,
                STAFF_ID,
                SKILL_ID,
                SKILL_LEVEL,
                NOTES
        );
    }

    @Nested
    @DisplayName("Event creation")
    class CreationTests {

        @Test
        @DisplayName("Should create an event with the supplied values")
        void shouldCreateEventWithSuppliedValues() {
            SkillAllocatedEvent event = createEvent();

            assertNull(event.id());
            assertEquals(PORTFOLIO_ID, event.getPortfolioId());
            assertEquals(STAFF_ID, event.getStaffId());
            assertEquals(SKILL_ID, event.getSkillId());
            assertEquals(SKILL_LEVEL, event.getSkillLevel());
            assertEquals(NOTES, event.getNotes());
        }

        @Test
        @DisplayName("Should create an event with a supplied ID")
        void shouldCreateEventWithSuppliedId() {
            Long id = 100L;

            SkillAllocatedEvent event = new SkillAllocatedEvent(
                    id,
                    PORTFOLIO_ID,
                    STAFF_ID,
                    SKILL_ID,
                    SKILL_LEVEL,
                    NOTES
            );

            assertEquals(id, event.id());
            assertEquals(id, event.getId());
            assertEquals(PORTFOLIO_ID, event.getPortfolioId());
            assertEquals(STAFF_ID, event.getStaffId());
            assertEquals(SKILL_ID, event.getSkillId());
            assertEquals(SKILL_LEVEL, event.getSkillLevel());
            assertEquals(NOTES, event.getNotes());
        }
    }

    @Nested
    @DisplayName("Event properties")
    class PropertyTests {

        @Test
        @DisplayName("Should return the portfolio ID")
        void shouldReturnPortfolioId() {
            SkillAllocatedEvent event = createEvent();

            assertEquals(PORTFOLIO_ID, event.getPortfolioId());
        }

        @Test
        @DisplayName("Should return the staff ID")
        void shouldReturnStaffId() {
            SkillAllocatedEvent event = createEvent();

            assertEquals(STAFF_ID, event.getStaffId());
        }

        @Test
        @DisplayName("Should return the skill ID")
        void shouldReturnSkillId() {
            SkillAllocatedEvent event = createEvent();

            assertEquals(SKILL_ID, event.getSkillId());
        }

        @Test
        @DisplayName("Should return the skill level")
        void shouldReturnSkillLevel() {
            SkillAllocatedEvent event = createEvent();

            assertEquals(SKILL_LEVEL, event.getSkillLevel());
        }

        @Test
        @DisplayName("Should return the notes")
        void shouldReturnNotes() {
            SkillAllocatedEvent event = createEvent();

            assertEquals(NOTES, event.getNotes());
        }

        @Test
        @DisplayName("Should return null ID for a newly created event")
        void shouldReturnNullIdForNewEvent() {
            SkillAllocatedEvent event = createEvent();

            assertNull(event.id());
        }
    }

    @Nested
    @DisplayName("withId")
    class WithIdTests {

        @Test
        @DisplayName("Should return a new event with the supplied ID")
        void shouldReturnNewEventWithSuppliedId() {
            SkillAllocatedEvent original = createEvent();
            Long newId = 42L;

            Event result = original.withId(newId);

            assertInstanceOf(
                    SkillAllocatedEvent.class,
                    result
            );

            SkillAllocatedEvent updated =
                    (SkillAllocatedEvent) result;

            assertEquals(newId, updated.id());
        }

        @Test
        @DisplayName("Should preserve all event data when assigning an ID")
        void shouldPreserveEventDataWhenAssigningId() {
            SkillAllocatedEvent original = createEvent();
            Long newId = 42L;

            SkillAllocatedEvent updated =
                    (SkillAllocatedEvent) original.withId(newId);

            assertEquals(newId, updated.id());
            assertEquals(
                    original.getPortfolioId(),
                    updated.getPortfolioId()
            );
            assertEquals(
                    original.getStaffId(),
                    updated.getStaffId()
            );
            assertEquals(
                    original.getSkillId(),
                    updated.getSkillId()
            );
            assertEquals(
                    original.getSkillLevel(),
                    updated.getSkillLevel()
            );
            assertEquals(
                    original.getNotes(),
                    updated.getNotes()
            );
        }

        @Test
        @DisplayName("Should not modify the original event")
        void shouldNotModifyOriginalEvent() {
            SkillAllocatedEvent original = createEvent();

            SkillAllocatedEvent updated =
                    (SkillAllocatedEvent) original.withId(99L);

            assertNull(original.id());
            assertEquals(99L, updated.id());
        }

        @Test
        @DisplayName("Should accept a null ID")
        void shouldAcceptNullId() {
            SkillAllocatedEvent original = new SkillAllocatedEvent(
                    10L,
                    PORTFOLIO_ID,
                    STAFF_ID,
                    SKILL_ID,
                    SKILL_LEVEL,
                    NOTES
            );

            SkillAllocatedEvent updated =
                    (SkillAllocatedEvent) original.withId(null);

            assertNull(updated.id());
            assertEquals(
                    PORTFOLIO_ID,
                    updated.getPortfolioId()
            );
            assertEquals(
                    STAFF_ID,
                    updated.getStaffId()
            );
            assertEquals(
                    SKILL_ID,
                    updated.getSkillId()
            );
            assertEquals(
                    SKILL_LEVEL,
                    updated.getSkillLevel()
            );
            assertEquals(
                    NOTES,
                    updated.getNotes()
            );
        }
    }

    @Nested
    @DisplayName("Null values")
    class NullValueTests {

        @Test
        @DisplayName("Should preserve null values supplied to the constructor")
        void shouldPreserveNullValues() {
            SkillAllocatedEvent event = new SkillAllocatedEvent(
                    null,
                    null,
                    null,
                    0,
                    null
            );

            assertNull(event.id());
            assertNull(event.getPortfolioId());
            assertNull(event.getStaffId());
            assertNull(event.getSkillId());
            assertEquals(0, event.getSkillLevel());
            assertNull(event.getNotes());
        }
    }
}