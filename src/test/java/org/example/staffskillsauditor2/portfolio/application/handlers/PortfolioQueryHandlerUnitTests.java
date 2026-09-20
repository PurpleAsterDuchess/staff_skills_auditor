package org.example.staffskillsauditor2.portfolio.application.handlers;

import org.example.staffskillsauditor2.portfolio.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.portfolio.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.portfolio.domain.exceptions.PortfolioNotFoundException;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioEntryJpa;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.portfolio.persistance.repositories.PortfolioEntryRepository;
import org.example.staffskillsauditor2.portfolio.persistance.repositories.PortfolioRepository;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioQueryHandlerUnitTests {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private PortfolioEntryRepository portfolioEntryRepository;

    private PortfolioQueryHandler handler;

    @BeforeEach
    void setUp() {
        handler = new PortfolioQueryHandler(
                portfolioRepository,
                portfolioEntryRepository
        );
    }

    @Test
    void shouldFindPortfolioById() {
        PortfolioJpa portfolio = createPortfolio();

        when(portfolioRepository.findById("PORTFOLIO001"))
                .thenReturn(Optional.of(portfolio));

        PortfolioDTO result =
                handler.findPortfolioById("PORTFOLIO001");

        assertNotNull(result);
        assertEquals("PORTFOLIO001", result.id());
        assertEquals("STAFF001", result.staff_id());
        assertNotNull(result.portfolioEntry());
        assertEquals(1, result.portfolioEntry().size());

        PortfolioEntryDTO entry = result.portfolioEntry().get(0);

        assertEquals("SKILL001", entry.skill_id());
        assertEquals(4, entry.skill_level());
        assertEquals("Good knowledge", entry.notes());
        assertEquals("PENDING", entry.verification_status());

        verify(portfolioRepository).findById("PORTFOLIO001");
    }

    @Test
    void shouldThrowExceptionWhenPortfolioDoesNotExist() {
        when(portfolioRepository.findById("PORTFOLIO001"))
                .thenReturn(Optional.empty());

        assertThrows(
                PortfolioNotFoundException.class,
                () -> handler.findPortfolioById("PORTFOLIO001")
        );
    }

    @Test
    void shouldRejectNullPortfolioId() {
        assertThrows(
                NullPointerException.class,
                () -> handler.findPortfolioById(null)
        );

        verifyNoInteractions(portfolioRepository);
    }

    @Test
    void shouldFindPendingSkills() {
        PortfolioEntryJpa pendingEntry =
                createEntry(
                        1,
                        "SKILL001",
                        4,
                        "Pending skill",
                        "PENDING"
                );

        when(portfolioEntryRepository.findByVerificationStatus("PENDING"))
                .thenReturn(List.of(pendingEntry));

        List<PortfolioEntryDTO> result =
                handler.findPendingSkills();

        assertEquals(1, result.size());

        PortfolioEntryDTO dto = result.get(0);

        assertEquals(1, dto.id());
        assertEquals("SKILL001", dto.skill_id());
        assertEquals(4, dto.skill_level());
        assertEquals("Pending skill", dto.notes());
        assertEquals("PENDING", dto.verification_status());

        verify(portfolioEntryRepository)
                .findByVerificationStatus("PENDING");
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoPendingSkills() {
        when(portfolioEntryRepository.findByVerificationStatus("PENDING"))
                .thenReturn(List.of());

        List<PortfolioEntryDTO> result =
                handler.findPendingSkills();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindExpiredSkills() {
        LocalDate expiredDate = LocalDate.now().minusDays(1);

        PortfolioEntryJpa expiredEntry =
                createEntry(
                        1,
                        "SKILL001",
                        3,
                        "Expired certification",
                        "VERIFIED"
                );

        expiredEntry.setExpirationDate(expiredDate);

        when(portfolioEntryRepository.findByExpirationDateBefore(
                any(LocalDate.class)
        )).thenReturn(List.of(expiredEntry));

        List<PortfolioEntryDTO> result =
                handler.findExpiredSkills();

        assertEquals(1, result.size());

        PortfolioEntryDTO dto = result.get(0);

        assertEquals("SKILL001", dto.skill_id());
        assertEquals(expiredDate, dto.expiration_date());
        assertEquals("Expired certification", dto.notes());

        verify(portfolioEntryRepository)
                .findByExpirationDateBefore(LocalDate.now());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoExpiredSkills() {
        when(portfolioEntryRepository.findByExpirationDateBefore(
                any(LocalDate.class)
        )).thenReturn(List.of());

        List<PortfolioEntryDTO> result =
                handler.findExpiredSkills();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindFilteredSkills() {
        PortfolioEntryJpa entry =
                createEntry(
                        1,
                        "SKILL001",
                        4,
                        "Java experience",
                        "PENDING"
                );

        when(portfolioEntryRepository.findAll(
                any(Specification.class)
        )).thenReturn(List.of(entry));

        List<PortfolioEntryDTO> result =
                handler.findFilteredSkills(
                        "STAFF001",
                        "SKILL001",
                        4
                );

        assertEquals(1, result.size());

        PortfolioEntryDTO dto = result.get(0);

        assertEquals("SKILL001", dto.skill_id());
        assertEquals(4, dto.skill_level());
        assertEquals("Java experience", dto.notes());

        verify(portfolioEntryRepository)
                .findAll(any(Specification.class));
    }

    @Test
    void shouldFindFilteredSkillsWithoutFilters() {
        PortfolioEntryJpa entry =
                createEntry(
                        1,
                        "SKILL001",
                        4,
                        "Java experience",
                        "PENDING"
                );

        when(portfolioEntryRepository.findAll(
                any(Specification.class)
        )).thenReturn(List.of(entry));

        List<PortfolioEntryDTO> result =
                handler.findFilteredSkills(
                        null,
                        null,
                        null
                );

        assertEquals(1, result.size());

        assertEquals(
                "SKILL001",
                result.get(0).skill_id()
        );

        verify(portfolioEntryRepository)
                .findAll(any(Specification.class));
    }

    @Test
    void shouldIgnoreBlankFilterValues() {
        PortfolioEntryJpa entry =
                createEntry(
                        1,
                        "SKILL001",
                        4,
                        "Java experience",
                        "PENDING"
                );

        when(portfolioEntryRepository.findAll(
                any(Specification.class)
        )).thenReturn(List.of(entry));

        List<PortfolioEntryDTO> result =
                handler.findFilteredSkills(
                        "   ",
                        "   ",
                        null
                );

        assertEquals(1, result.size());

        verify(portfolioEntryRepository)
                .findAll(any(Specification.class));
    }

    private PortfolioJpa createPortfolio() {
        PortfolioJpa portfolio = new PortfolioJpa();

        portfolio.setId("PORTFOLIO001");
        portfolio.setStaffId("STAFF001");
        portfolio.setPortfolioEntry(new ArrayList<>());

        PortfolioEntryJpa entry =
                createEntry(
                        1,
                        "SKILL001",
                        4,
                        "Good knowledge",
                        "PENDING"
                );

        entry.setPortfolio(portfolio);
        portfolio.getPortfolioEntry().add(entry);

        return portfolio;
    }

    private PortfolioEntryJpa createEntry(
            Integer id,
            String skillId,
            Integer level,
            String notes,
            String status
    ) {
        PortfolioEntryJpa entry = new PortfolioEntryJpa();

        entry.setId(id);

        SkillJpa skill = new SkillJpa();
        skill.setId(skillId);

        entry.setSkill(skill);
        entry.setSkillLevel(level);
        entry.setNotes(notes);
        entry.setVerificationStatus(status);

        return entry;
    }
}