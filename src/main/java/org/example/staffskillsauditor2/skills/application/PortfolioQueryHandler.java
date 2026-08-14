package org.example.staffskillsauditor2.skills.application;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.exceptions.PortfolioNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.PortfolioJpaToDTOMapper;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import org.slf4j.Logger;

@AllArgsConstructor
@Service
public class PortfolioQueryHandler {
        private PortfolioRepository portfolioRepository;
        private final Logger LOG = LoggerFactory.getLogger(getClass());

        public PortfolioDTO findPortfolioById(String portfolioId) {
            Objects.requireNonNull(portfolioId, "Portfolio ID cannot be null");

            PortfolioJpa jpaEntity = portfolioRepository.findById(portfolioId)
                    .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found for ID: " + portfolioId));

            return PortfolioJpaToDTOMapper.toPortfolioDTO(jpaEntity);
        }
}
