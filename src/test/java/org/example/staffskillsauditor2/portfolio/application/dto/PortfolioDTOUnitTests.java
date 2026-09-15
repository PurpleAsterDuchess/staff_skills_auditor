package org.example.staffskillsauditor2.portfolio.application.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PortfolioDTO Unit Tests")
class PortfolioDTOUnitTests {

    @Test
    @DisplayName("Should create PortfolioDTO with all supplied values")
    void shouldCreatePortfolioDTOWithAllValues() {
        PortfolioEntryDTO entry = createEntry();

        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                "STAF001",
                List.of(entry)
        );

        assertEquals("PORTFOLIO001", dto.id());
        assertEquals("STAFF001", dto.staff_id());
        assertEquals(1, dto.portfolioEntry().size());
        assertEquals(entry, dto.portfolioEntry().get(0));
    }

    @Test
    @DisplayName("Should create empty list when portfolio entries are null")
    void shouldCreateEmptyListWhenPortfolioEntriesAreNull() {
        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                null
        );

        assertNotNull(dto.portfolioEntry());
        assertTrue(dto.portfolioEntry().isEmpty());
    }

    @Test
    @DisplayName("Should preserve all portfolio entries")
    void shouldPreserveAllPortfolioEntries() {
        PortfolioEntryDTO firstEntry = createEntry(
                "SKILL001",
                3
        );

        PortfolioEntryDTO secondEntry = createEntry(
                "SKILL002",
                5
        );

        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                List.of(firstEntry, secondEntry)
        );

        assertEquals(2, dto.portfolioEntry().size());
        assertEquals(firstEntry, dto.portfolioEntry().get(0));
        assertEquals(secondEntry, dto.portfolioEntry().get(1));
    }

    @Test
    @DisplayName("Should make a copy of portfolio entries")
    void shouldMakeCopyOfPortfolioEntries() {
        List<PortfolioEntryDTO> entries = new ArrayList<>();
        PortfolioEntryDTO entry = createEntry();

        entries.add(entry);

        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                entries
        );

        entries.add(
                createEntry("SKILL002", 4)
        );

        assertEquals(1, dto.portfolioEntry().size());
        assertEquals(entry, dto.portfolioEntry().get(0));
    }

    @Test
    @DisplayName("Should return an unmodifiable portfolio entry list")
    void shouldReturnUnmodifiablePortfolioEntryList() {
        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                List.of(createEntry())
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> dto.portfolioEntry().add(createEntry())
        );
    }

    @Test
    @DisplayName("Should allow null portfolio ID")
    void shouldAllowNullPortfolioId() {
        PortfolioDTO dto = new PortfolioDTO(
                null,
                "STAFF001",
                List.of()
        );

        assertNull(dto.id());
    }

    @Test
    @DisplayName("Should allow null staff ID")
    void shouldAllowNullStaffId() {
        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO001",
                null,
                List.of()
        );

        assertNull(dto.staff_id());
    }

    @Test
    @DisplayName("Should consider DTOs with the same values equal")
    void shouldConsiderDTOsWithSameValuesEqual() {
        PortfolioEntryDTO entry = createEntry();

        PortfolioDTO first = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                List.of(entry)
        );

        PortfolioDTO second = new PortfolioDTO(
                "PORTFOLIO001",
                "STAFF001",
                List.of(entry)
        );

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("Should return correct values from record accessors")
    void shouldReturnCorrectValuesFromRecordAccessors() {
        PortfolioEntryDTO entry = createEntry();

        PortfolioDTO dto = new PortfolioDTO(
                "PORTFOLIO02",
                "STAFF002",
                List.of(entry)
        );

        assertEquals("PORTFOLIO002", dto.id());
        assertEquals("STAFF002", dto.staff_id());
        assertEquals(List.of(entry), dto.portfolioEntry());
    }

    private PortfolioEntryDTO createEntry() {
        return createEntry("SKILL001", 3);
    }

    private PortfolioEntryDTO createEntry(
            String skillId,
            int skillLevel
    ) {
        return new PortfolioEntryDTO(
                1,
                "PORTFOLIO001",
                skillId,
                skillLevel,
                null,
                "Relevant experience",
                "PENDING",
                null,
                null
        );
    }
}