package org.example.staffskillsauditor2.portfolio.domain;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PortfolioEntry Domain Unit Tests")
class PortfolioEntryUnitTests {

    private static final String SKILL_ID = "SKL100";
    private static final String NOTES = "  Relevant project experience  ";

    private PortfolioEntry createValidEntry() {
        return new PortfolioEntry(SKILL_ID, 3, NOTES);
    }

    private PortfolioEntry createVerifiedEntry() {
        PortfolioEntry entry = createValidEntry();
        entry.verify(
                "manager1",
                LocalDateTime.of(2026, 9, 12, 10, 30)
        );
        return entry;
    }

    @Nested
    @DisplayName("Creation and validation")
    class CreationTests {

        @Test
        @DisplayName("Should create a valid portfolio entry")
        void validEntryCreation() {
            PortfolioEntry entry = createValidEntry();

            assertNotNull(entry.id());
            assertEquals(SKILL_ID, entry.skillId());
            assertEquals(3, entry.skillLevel());
            assertNull(entry.expirationDate());
            assertEquals(
                    "Relevant project experience",
                    entry.notes()
            );
            Assertions.assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );
            assertNull(entry.verifiedBy());
            assertNull(entry.verifiedOn());
        }

        @Test
        @DisplayName("Should default null notes to an empty string")
        void nullNotesBecomeEmptyString() {
            PortfolioEntry entry =
                    new PortfolioEntry(SKILL_ID, 1, null);

            assertEquals("", entry.notes());
        }

        @Test
        @DisplayName("Should reject a null skill ID")
        void nullSkillIdThrowsException() {
            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new PortfolioEntry(
                                    null,
                                    3,
                                    NOTES
                            )
                    );

            assertEquals(
                    "Skill ID cannot be blank",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should reject a blank skill ID")
        void blankSkillIdThrowsException() {
            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new PortfolioEntry(
                                    "   ",
                                    3,
                                    NOTES
                            )
                    );

            assertEquals(
                    "Skill ID cannot be blank",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should reject skill level below 1")
        void skillLevelBelowMinimumThrowsException() {
            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new PortfolioEntry(
                                    SKILL_ID,
                                    0,
                                    NOTES
                            )
                    );

            assertEquals(
                    "Skill level must be between 1 and 5",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should reject skill level above 5")
        void skillLevelAboveMaximumThrowsException() {
            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> new PortfolioEntry(
                                    SKILL_ID,
                                    6,
                                    NOTES
                            )
                    );

            assertEquals(
                    "Skill level must be between 1 and 5",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should accept skill level 1")
        void minimumSkillLevelIsAccepted() {
            assertDoesNotThrow(
                    () -> new PortfolioEntry(
                            SKILL_ID,
                            1,
                            NOTES
                    )
            );
        }

        @Test
        @DisplayName("Should accept skill level 5")
        void maximumSkillLevelIsAccepted() {
            assertDoesNotThrow(
                    () -> new PortfolioEntry(
                            SKILL_ID,
                            5,
                            NOTES
                    )
            );
        }

        @Test
        @DisplayName("Should default null verification status to pending")
        void nullVerificationStatusDefaultsToPending() {
            PortfolioEntry entry = new PortfolioEntry(
                    Identity.generateId(),
                    SKILL_ID,
                    4,
                    LocalDate.of(2027, 9, 12),
                    NOTES,
                    null,
                    null,
                    null
            );

            assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );

            assertEquals(
                    LocalDate.of(2027, 9, 12),
                    entry.expirationDate()
            );
        }
    }

    @Nested
    @DisplayName("Editing")
    class EditTests {

        @Test
        @DisplayName("Should update skill level and notes")
        void editUpdatesDetails() {
            PortfolioEntry entry = createValidEntry();

            entry.edit(
                    5,
                    "  Updated notes  "
            );

            assertEquals(5, entry.skillLevel());
            assertEquals(
                    "Updated notes",
                    entry.notes()
            );
        }

        @Test
        @DisplayName("Should reset verification when edited")
        void editResetsVerification() {
            PortfolioEntry entry =
                    createVerifiedEntry();

            entry.edit(
                    4,
                    "Updated after verification"
            );

            assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );
            assertNull(entry.verifiedBy());
            assertNull(entry.verifiedOn());
        }

        @Test
        @DisplayName("Should leave existing values unchanged after an invalid edit and reject it")
        void invalidEditThrowsException() {
            PortfolioEntry entry = createValidEntry();

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> entry.edit(
                                    6,
                                    "New notes"
                            )
                    );

            assertEquals(3, entry.skillLevel());

            assertEquals(
                    "Skill level must be between 1 and 5",
                    exception.getMessage()
            );

            assertEquals(
                    "Relevant project experience",
                    entry.notes()
            );
        }

        @Test
        @DisplayName("Should convert null notes to an empty string when editing")
        void editNullNotesBecomeEmptyString() {
            PortfolioEntry entry = createValidEntry();

            entry.edit(2, null);

            assertEquals("", entry.notes());
        }
    }

    @Nested
    @DisplayName("Verification")
    class VerificationTests {

        @Test
        @DisplayName("Should verify a portfolio entry")
        void verifySetsVerificationDetails() {
            PortfolioEntry entry = createValidEntry();

            LocalDateTime verifiedOn =
                    LocalDateTime.of(
                            2026,
                            9,
                            12,
                            11,
                            0
                    );

            entry.verify(
                    "manager1",
                    verifiedOn
            );

            assertEquals(
                    VerificationStatus.VERIFIED,
                    entry.verificationStatus()
            );
            assertEquals(
                    "manager1",
                    entry.verifiedBy()
            );
            assertEquals(
                    verifiedOn,
                    entry.verifiedOn()
            );
        }

        @Test
        @DisplayName("Should reject a blank verifier")
        void blankVerifierThrowsException() {
            PortfolioEntry entry = createValidEntry();

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> entry.verify(
                                    "   ",
                                    LocalDateTime.now()
                            )
                    );

            assertEquals(
                    "Verifier cannot be blank",
                    exception.getMessage()
            );

            assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );
        }

        @Test
        @DisplayName("Should reject a null verifier")
        void nullVerifierThrowsException() {
            PortfolioEntry entry = createValidEntry();

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> entry.verify(
                                    null,
                                    LocalDateTime.now()
                            )
                    );

            assertEquals(
                    "Verifier cannot be blank",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should reject a null verification date")
        void nullVerificationDateThrowsException() {
            PortfolioEntry entry = createValidEntry();

            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> entry.verify(
                                    "manager1",
                                    null
                            )
                    );

            assertEquals(
                    "Verification date cannot be null",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should unverify a verified entry")
        void unverifyClearsVerificationDetails() {
            PortfolioEntry entry =
                    createVerifiedEntry();

            entry.unverify();

            assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );
            assertNull(entry.verifiedBy());
            assertNull(entry.verifiedOn());
        }
    }

    @Nested
    @DisplayName("Rejection")
    class RejectionTests {

        @Test
        @DisplayName("Should reject a portfolio entry")
        void rejectSetsRejectionDetails() {
            PortfolioEntry entry = createValidEntry();

            LocalDateTime rejectedOn =
                    LocalDateTime.of(
                            2026,
                            9,
                            12,
                            12,
                            0
                    );

            entry.reject(
                    "manager2",
                    rejectedOn
            );

            assertEquals(
                    VerificationStatus.REJECTED,
                    entry.verificationStatus()
            );
            assertEquals(
                    "manager2",
                    entry.verifiedBy()
            );
            assertEquals(
                    rejectedOn,
                    entry.verifiedOn()
            );
        }

        @Test
        @DisplayName("Should reject a blank rejector")
        void blankRejectorThrowsException() {
            PortfolioEntry entry = createValidEntry();

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> entry.reject(
                                    "   ",
                                    LocalDateTime.now()
                            )
                    );

            assertEquals(
                    "Rejector cannot be blank",
                    exception.getMessage()
            );

            assertEquals(
                    VerificationStatus.PENDING,
                    entry.verificationStatus()
            );
        }

        @Test
        @DisplayName("Should reject a null rejector")
        void nullRejectorThrowsException() {
            PortfolioEntry entry = createValidEntry();

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> entry.reject(
                                    null,
                                    LocalDateTime.now()
                            )
                    );

            assertEquals(
                    "Rejector cannot be blank",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should reject a null rejection date")
        void nullRejectionDateThrowsException() {
            PortfolioEntry entry = createValidEntry();

            NullPointerException exception =
                    assertThrows(
                            NullPointerException.class,
                            () -> entry.reject(
                                    "manager2",
                                    null
                            )
                    );

            assertEquals(
                    "Rejection date cannot be null",
                    exception.getMessage()
            );
        }

    }
}