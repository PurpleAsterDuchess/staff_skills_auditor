package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.application.dto.PortfolioDTO;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioEntryDTO;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;

import java.util.List;
import java.util.Objects;


public class PortfolioJpaToDTOMapper {

    public static PortfolioDTO toPortfolioDTO(PortfolioJpa portfolio) {

        Objects.requireNonNull(
                portfolio,
                "Skill Portfolio JPA entry cannot be null"
        );

        List<PortfolioEntryDTO> entryDTOs =
                portfolio.getPortfolioEntry() == null
                        ? List.of()
                        : portfolio.getPortfolioEntry()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(PortfolioEntryJpaToDTOMapper::toPortfolioEntryDTO)
                        .toList();

        return new PortfolioDTO(
                portfolio.getId(),
                portfolio.getStaffId(),
                entryDTOs
        );
    }
}