package org.example.staffskillsauditor2.skills.application.handlers;

import lombok.AllArgsConstructor;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.skills.application.exceptions.PortfolioNotFoundException;
import org.example.staffskillsauditor2.skills.application.mappers.PortfolioJpaToDTOMapper;
import org.example.staffskillsauditor2.skills.application.mappers.PortfolioEntryJpaToDTOMapper;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioRepository;
import org.example.staffskillsauditor2.skills.persistance.repositories.PortfolioEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PortfolioQueryHandler {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioEntryRepository portfolioEntryRepository;

    public PortfolioDTO findPortfolioById(String portfolioId) {
        Objects.requireNonNull(portfolioId, "Portfolio ID cannot be null");
        PortfolioJpa jpaEntity = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found for ID: " + portfolioId));
        return PortfolioJpaToDTOMapper.toPortfolioDTO(jpaEntity);
    }

    public List<PortfolioEntryDTO> findPendingSkills() {
        return portfolioEntryRepository.findByVerificationStatus("PENDING").stream()
                .map(PortfolioEntryJpaToDTOMapper::toPortfolioEntryDTO)
                .collect(Collectors.toList());
    }

    public List<PortfolioEntryDTO> findExpiredSkills() {
        return portfolioEntryRepository.findByExpirationDateBefore(LocalDate.now()).stream()
                .map(PortfolioEntryJpaToDTOMapper::toPortfolioEntryDTO)
                .collect(Collectors.toList());
    }


}
