package org.example.staffskillsauditor2.portfolio.application.handlers;

import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.portfolio.domain.exceptions.PortfolioNotFoundException;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioEntryJpa;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.portfolio.persistance.repositories.PortfolioRepository;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioCommandHandlerUnitTests {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private DomainEventManager domainEventManager;

    private PortfolioCommandHandler handler;

    @BeforeEach
    void setUp() {
        handler = new PortfolioCommandHandler(
                portfolioRepository,
                domainEventManager
        );
    }

    @Test
    void shouldAllocateSkillToPortfolio() {
        PortfolioJpa portfolio = createPortfolio();

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        handler.allocateSkillToPortfolio(
                "STAFF001",
                "SKILL001",
                4,
                "Good working knowledge"
        );

        assertEquals(1, portfolio.getPortfolioEntry().size());

        PortfolioEntryJpa entry = portfolio.getPortfolioEntry().getFirst();

        assertEquals("SKILL001", entry.getSkill().getId());
        assertEquals(4, entry.getSkillLevel());
        assertEquals("Good working knowledge", entry.getNotes());
        assertEquals("PENDING", entry.getVerificationStatus());

        verify(portfolioRepository).save(portfolio);
        verify(domainEventManager).manageDomainEvents(
                eq("PortfolioCommandHandler"),
                anyList()
        );
    }

    @Test
    void shouldNotAllocateSkillWhenPortfolioDoesNotExist() {
        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.empty());

        assertThrows(
                PortfolioNotFoundException.class,
                () -> handler.allocateSkillToPortfolio(
                        "STAFF001",
                        "SKILL001",
                        4,
                        "Notes"
                )
        );

        verify(portfolioRepository, never()).save(any());
        verifyNoInteractions(domainEventManager);
    }

    @Test
    void shouldRejectInvalidSkillLevelWhenAllocatingSkill() {
        PortfolioJpa portfolio = createPortfolio();

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        assertThrows(
                IllegalArgumentException.class,
                () -> handler.allocateSkillToPortfolio(
                        "STAFF001",
                        "SKILL001",
                        6,
                        "Notes"
                )
        );

        verify(portfolioRepository, never()).save(any());
        verifyNoInteractions(domainEventManager);
    }

    @Test
    void shouldEditExistingSkill() {
        PortfolioJpa portfolio = createPortfolioWithSkill(
                "SKILL001",
                2,
                "Old notes"
        );

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        handler.editSkillInPortfolio(
                "STAFF001",
                "SKILL001",
                5,
                "Updated notes"
        );

        PortfolioEntryJpa entry = portfolio.getPortfolioEntry().getFirst();

        assertEquals(5, entry.getSkillLevel());
        assertEquals("Updated notes", entry.getNotes());

        verify(portfolioRepository).save(portfolio);
        verify(domainEventManager).manageDomainEvents(
                eq("PortfolioCommandHandler"),
                anyList()
        );
    }

    @Test
    void shouldNotEditSkillWhenPortfolioDoesNotExist() {
        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.empty());

        assertThrows(
                PortfolioNotFoundException.class,
                () -> handler.editSkillInPortfolio(
                        "STAFF001",
                        "SKILL001",
                        5,
                        "Updated notes"
                )
        );

        verify(portfolioRepository, never()).save(any());
        verifyNoInteractions(domainEventManager);
    }

    @Test
    void shouldNotEditSkillThatDoesNotExist() {
        PortfolioJpa portfolio = createPortfolio();

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        assertThrows(
                RuntimeException.class,
                () -> handler.editSkillInPortfolio(
                        "STAFF001",
                        "SKILL001",
                        5,
                        "Updated notes"
                )
        );

        verify(portfolioRepository, never()).save(any());
    }

    @Test
    void shouldVerifySkill() {
        PortfolioJpa portfolio = createPortfolioWithSkill(
                "SKILL001",
                4,
                "Good knowledge"
        );

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        handler.verifySkillInPortfolio(
                "STAFF001",
                "SKILL001",
                "MANAGER001"
        );

        PortfolioEntryJpa entry = portfolio.getPortfolioEntry().getFirst();

        assertEquals("VERIFIED", entry.getVerificationStatus());
        assertEquals("MANAGER001", entry.getVerifiedBy());
        assertNotNull(entry.getVerifiedOn());

        verify(portfolioRepository).save(portfolio);
    }

    @Test
    void shouldNotVerifySkillWhenPortfolioDoesNotExist() {
        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.empty());

        assertThrows(
                PortfolioNotFoundException.class,
                () -> handler.verifySkillInPortfolio(
                        "STAFF001",
                        "SKILL001",
                        "MANAGER001"
                )
        );

        verify(portfolioRepository, never()).save(any());
    }

    @Test
    void shouldUnverifySkill() {
        PortfolioJpa portfolio = createPortfolioWithSkill(
                "SKILL001",
                4,
                "Good knowledge"
        );

        PortfolioEntryJpa entry = portfolio.getPortfolioEntry().getFirst();
        entry.setVerificationStatus("VERIFIED");
        entry.setVerifiedBy("MANAGER001");

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        handler.unverifySkillInPortfolio(
                "STAFF001",
                "SKILL001",
                "MANAGER001"
        );

        assertEquals(
                "PENDING",
                entry.getVerificationStatus()
        );

        assertNull(entry.getVerifiedBy());
        assertNull(entry.getVerifiedOn());

        verify(portfolioRepository).save(portfolio);
    }

    @Test
    void shouldRejectSkill() {
        PortfolioJpa portfolio = createPortfolioWithSkill(
                "SKILL001",
                4,
                "Good knowledge"
        );

        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.of(portfolio));

        handler.rejectSkillInPortfolio(
                "STAFF001",
                "SKILL001",
                "MANAGER001"
        );

        PortfolioEntryJpa entry = portfolio.getPortfolioEntry().getFirst();

        assertEquals(
                "REJECTED",
                entry.getVerificationStatus()
        );

        assertEquals(
                "MANAGER001",
                entry.getVerifiedBy()
        );

        assertNotNull(entry.getVerifiedOn());

        verify(portfolioRepository).save(portfolio);
    }

    @Test
    void shouldNotRejectSkillWhenPortfolioDoesNotExist() {
        when(portfolioRepository.findByStaffId("STAFF001"))
                .thenReturn(Optional.empty());

        assertThrows(
                PortfolioNotFoundException.class,
                () -> handler.rejectSkillInPortfolio(
                        "STAFF001",
                        "SKILL001",
                        "MANAGER001"
                )
        );

        verify(portfolioRepository, never()).save(any());
    }

    private PortfolioJpa createPortfolio() {
        PortfolioJpa portfolio = new PortfolioJpa();

        portfolio.setId("PORTFOLIO001");
        portfolio.setStaffId("STAFF001");
        portfolio.setPortfolioEntry(new ArrayList<>());

        return portfolio;
    }

    private PortfolioJpa createPortfolioWithSkill(
            String skillId,
            int skillLevel,
            String notes
    ) {
        PortfolioJpa portfolio = createPortfolio();

        SkillJpa skill = new SkillJpa();
        skill.setId(skillId);

        PortfolioEntryJpa entry = new PortfolioEntryJpa();
        entry.setId(1);
        entry.setPortfolio(portfolio);
        entry.setSkill(skill);
        entry.setSkillLevel(skillLevel);
        entry.setNotes(notes);
        entry.setVerificationStatus("PENDING");

        portfolio.getPortfolioEntry().add(entry);

        return portfolio;
    }
}