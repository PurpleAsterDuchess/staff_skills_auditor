package org.example.staffskillsauditor2.skills.application;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.common.events.DomainEventManager;
import org.example.staffskillsauditor2.skills.application.exceptions.PortfolioNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.PortfolioJpaToDomainMapper;
import org.example.staffskillsauditor2.skills.application.mappers.PortfolioToJpaMapper;
import org.example.staffskillsauditor2.skills.domain.SkillPortfolio;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioCommandHandler {
    private final PortfolioRepository portfolioRepository;
    private final DomainEventManager domainEventManager; // Injected Outbox Coordinator [6, 17]

    @Transactional
    public void allocateSkillToPortfolio(String staffId, String skillId, int level, String notes) {
        PortfolioJpa jpaEntity = portfolioRepository.findByStaffId(staffId)
                .orElseThrow(() -> new PortfolioNotFoundException(staffId));

        SkillPortfolio portfolio = PortfolioJpaToDomainMapper.map(jpaEntity);

        portfolio.allocateSkill(skillId, level, notes);

        PortfolioToJpaMapper.map(portfolio, jpaEntity);
        portfolioRepository.save(jpaEntity);

        if (portfolio.domainEventsExist()) {
            domainEventManager.manageDomainEvents(this.getClass().getSimpleName(), portfolio.listOfDomainEvents());
            portfolio.clearDomainEvents();
        }
    }
}