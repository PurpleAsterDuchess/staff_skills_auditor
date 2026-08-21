package org.example.staffskillsauditor2.skills.application.dto;

import java.util.List;

public record PortfolioDTO(
        String id,
        String staff_id,
        List<org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa> portfolioEntry
){
    public PortfolioDTO {
        portfolioEntry = portfolioEntry == null ? List.of() : List.copyOf(portfolioEntry);
    }
}