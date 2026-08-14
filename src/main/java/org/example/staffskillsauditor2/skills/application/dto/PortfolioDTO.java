package org.example.staffskillsauditor2.skills.application.dto;

import java.util.List;

public record PortfolioDTO(
    String id,
    String staff_id,
    List<PortfolioEntryDTO> portfolioEntry
){
    public PortfolioDTO {
        portfolioEntry = portfolioEntry == null ? List.of() : List.copyOf(portfolioEntry);
    }
}