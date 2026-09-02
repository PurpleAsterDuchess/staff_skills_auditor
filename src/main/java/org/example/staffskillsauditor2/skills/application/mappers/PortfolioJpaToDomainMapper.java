package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.PortfolioEntry;
import org.example.staffskillsauditor2.skills.domain.SkillPortfolio;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PortfolioJpaToDomainMapper {

    public static SkillPortfolio map(PortfolioJpa jpa) {
        Objects.requireNonNull(jpa, "Portfolio JPA entity cannot be null");

        Identity<SkillPortfolio> id = Identity.of(jpa.getId());
        List<PortfolioEntry> entries = new ArrayList<>();

        if (jpa.getPortfolioEntry() != null) {
            for (PortfolioEntryJpa entryJpa : jpa.getPortfolioEntry()) {
                entries.add(new PortfolioEntry(
                        entryJpa.getId(),
                        entryJpa.getSkill() != null ? entryJpa.getSkill().getId() : null,
                        entryJpa.getSkillLevel(),
                        entryJpa.getExpirationDate(),
                        entryJpa.getNotes(),
                        entryJpa.getVerificationStatus(),
                        entryJpa.getVerifiedBy(),
                        entryJpa.getVerifiedOn()
                ));
            }
        }

        return SkillPortfolio.portfolioOf(id, jpa.getStaffId(), entries);
    }
}
