package org.example.staffskillsauditor2.portfolio.application.mappers;

import org.example.staffskillsauditor2.portfolio.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioEntryJpa;

import java.util.Objects;

public class PortfolioEntryJpaToDTOMapper {

    public static PortfolioEntryDTO toPortfolioEntryDTO(
            PortfolioEntryJpa portfolioEntry) {

        Objects.requireNonNull(
                portfolioEntry,
                "Portfolio entry JPA entity cannot be null"
        );

        return new PortfolioEntryDTO(
                portfolioEntry.getId(),

                portfolioEntry.getPortfolio() == null
                        ? null
                        : portfolioEntry.getPortfolio().getId(),

                portfolioEntry.getSkill() == null
                        ? null
                        : portfolioEntry.getSkill().getId(),

                portfolioEntry.getSkillLevel(),
                portfolioEntry.getExpirationDate(),
                portfolioEntry.getNotes(),
                portfolioEntry.getVerificationStatus(),
                portfolioEntry.getVerifiedBy(),
                portfolioEntry.getVerifiedOn()
        );
    }
}
