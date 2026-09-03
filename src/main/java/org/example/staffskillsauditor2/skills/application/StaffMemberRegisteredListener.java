package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.staffskillsauditor2.skills.domain.events.StaffMemberRegisteredEvent;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
@RabbitListener(queues = "skills-portfolio-sync")
public class StaffMemberRegisteredListener {

    private final PortfolioRepository portfolioRepository;

    @RabbitHandler
    public void receive(StaffMemberRegisteredEvent event) {
        log.info("CloudAMQP Listener received StaffMemberRegisteredEvent for staff ID: {}", event.getStaffId());

        boolean exists = portfolioRepository.findByStaffId(event.getStaffId()).isPresent();
        if (exists) {
            log.warn("Portfolio already exists for staff ID: {}. Skipping.", event.getStaffId());
            return;
        }

        PortfolioJpa portfolio = new PortfolioJpa();
        portfolio.setId(UUID.randomUUID().toString());
        portfolio.setStaffId(event.getStaffId());
        portfolioRepository.save(portfolio);

        log.info("Successfully provisioned new Remote-Driven Skill Portfolio with ID: {}", portfolio.getId());
    }
}
