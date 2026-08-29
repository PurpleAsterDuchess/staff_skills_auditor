package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.domain.events.CreateStaffMember;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PortfolioEventListener {

    private final PortfolioRepository portfolioRepository;
    private static final Logger LOG = LoggerFactory.getLogger(PortfolioEventListener.class);

    @ApplicationModuleListener
    public void onStaffMemberCreated(CreateStaffMember event) {
        LOG.info("Received CreateStaffMemberEvent for staff ID: {}", event.getStaffId());

        boolean exists = portfolioRepository.findByStaffId(event.getStaffId()).isPresent();
        if (exists) {
            LOG.warn("Skill portfolio already exists for staff ID: {}. Skipping.", event.getStaffId());
            return;
        }

        PortfolioJpa portfolio = new PortfolioJpa();
        portfolio.setId(UUID.randomUUID().toString());
        portfolio.setStaffId(event.getStaffId());

        portfolioRepository.save(portfolio);
        LOG.info("Successfully provisioned empty Skill Portfolio for staff ID: {}", event.getStaffId());
    }
}