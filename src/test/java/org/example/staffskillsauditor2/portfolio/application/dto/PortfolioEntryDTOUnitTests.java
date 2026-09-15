package org.example.staffskillsauditor2.portfolio.application.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PortfolioEntryDTO Unit Tests")
class PortfolioEntryDTOUnitTests {

    @Test
    @DisplayName("Should create PortfolioEntryDTO with all supplied values")
    void shouldCreatePortfolioEntryDTOWithAllValues() {
        LocalDate expirationDate = LocalDate.of(2027, 12, 31);
        LocalDateTime verifiedOn = LocalDateTime.of(2026, 9, 15, 10, 30);

        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                "SKILL001",
                4,
                expirationDate,
                "Relevant project experience",
                "VERIFIED",
                "MANAGER001",
                verifiedOn
        );

        assertEquals(1, dto.id());
        assertEquals("PORTFOLIO001", dto.portfolio_id());
        assertEquals("SKILL001", dto.skill_id());
        assertEquals(4, dto.skill_level());
        assertEquals(expirationDate, dto.expiration_date());
        assertEquals("Relevant project experience", dto.notes());
        assertEquals("VERIFIED", dto.verification_status());
        assertEquals("MANAGER001", dto.verified_by());
        assertEquals(verifiedOn, dto.verified_on());
    }

    @Test
    @DisplayName("Should return null for optional fields when null is supplied")
    void shouldAllowNullOptionalFields() {
        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                "SKILL001",
                3,
                null,
                null,
                "PENDING",
                null,
                null
        );

        assertNull(dto.expiration_date());
        assertNull(dto.notes());
        assertNull(dto.verified_by());
        assertNull(dto.verified_on());
    }

    @Test
    @DisplayName("Should return correct ID")
    void shouldReturnCorrectId() {
        PortfolioEntryDTO dto = createEntry();

        assertEquals(1, dto.id());
    }

    @Test
    @DisplayName("Should return correct portfolio ID")
    void shouldReturnCorrectPortfolioId() {
        PortfolioEntryDTO dto = createEntry();

        assertEquals("PORTFOLIO001", dto.portfolio_id());
    }

    @Test
    @DisplayName("Should return correct skill ID")
    void shouldReturnCorrectSkillId() {
        PortfolioEntryDTO dto = createEntry();

        assertEquals("SKILL001", dto.skill_id());
    }

    @Test
    @DisplayName("Should return correct skill level")
    void shouldReturnCorrectSkillLevel() {
        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                "SKILL001",
                5,
                null,
                "Advanced experience",
                "PENDING",
                null,
                null
        );

        assertEquals(5, dto.skill_level());
    }

    @Test
    @DisplayName("Should return correct verification details")
    void shouldReturnCorrectVerificationDetails() {
        LocalDateTime verifiedOn = LocalDateTime.of(2026, 9, 15, 12, 0);

        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                "SKILL001",
                4,
                null,
                "Verified experience",
                "VERIFIED",
                "MANAGER001",
                verifiedOn
        );

        assertEquals("VERIFIED", dto.verification_status());
        assertEquals("MANAGER001", dto.verified_by());
        assertEquals(verifiedOn, dto.verified_on());
    }

    @Test
    @DisplayName("Should consider DTOs with the same values equal")
    void shouldConsiderDTOsWithSameValuesEqual() {
        PortfolioEntryDTO first = createEntry();
        PortfolioEntryDTO second = createEntry();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("Should not consider DTOs with different values equal")
    void shouldNotConsiderDifferentDTOsEqual() {
        PortfolioEntryDTO first = createEntry();

        PortfolioEntryDTO second = new PortfolioEntryDTO(
                2,
                "PORTFOLIO002",
                "SKILL002",
                5,
                null,
                "Different experience",
                "REJECTED",
                "MANAGER002",
                null
        );

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Should support a null ID")
    void shouldAllowNullId() {
        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                null,
                "PORTFOLIO001",
                "SKILL001",
                3,
                null,
                "Some experience",
                "PENDING",
                null,
                null
        );

        assertNull(dto.id());
    }

    @Test
    @DisplayName("Should support null portfolio ID")
    void shouldAllowNullPortfolioId() {
        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                null,
                "SKILL001",
                3,
                null,
                "Some experience",
                "PENDING",
                null,
                null
        );

        assertNull(dto.portfolio_id());
    }

    @Test
    @DisplayName("Should support null skill ID")
    void shouldAllowNullSkillId() {
        PortfolioEntryDTO dto = new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                null,
                3,
                null,
                "Some experience",
                "PENDING",
                null,
                null
        );

        assertNull(dto.skill_id());
    }

    private PortfolioEntryDTO createEntry() {
        return new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                "SKILL001",
                3,
                LocalDate.of(2027, 12, 31),
                "Relevant experience",
                "PENDING",
                null,
                null
        );
    }
}