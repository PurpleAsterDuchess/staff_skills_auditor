package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;

import java.util.Objects;


public class PortfolioJpaToDTOMapper {
    public static PortfolioDTO toPortfolioDTO(PortfolioJpa portfolio) {
        Objects.requireNonNull(portfolio, "Skill Portfolio JPA entry cannot be null");

        return new PortfolioDTO(
                portfolio.getId(),
                portfolio.getStaffId(),
                portfolio.getPortfolioEntry()
        );
    }
}
