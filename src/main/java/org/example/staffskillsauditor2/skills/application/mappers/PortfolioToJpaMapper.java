package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.PortfolioEntry;
import org.example.staffskillsauditor2.skills.domain.SkillPortfolio;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

import java.util.Objects;

public class PortfolioToJpaMapper {

    public static void map(SkillPortfolio domain, PortfolioJpa jpa) {
        Objects.requireNonNull(domain, "Domain portfolio cannot be null");
        Objects.requireNonNull(jpa, "Jpa entity cannot be null");

        jpa.setId(domain.id().id());
        jpa.setStaffId(domain.staffId());

        for (PortfolioEntry domainEntry : domain.entries()) {
            boolean jpaExists = jpa.getPortfolioEntry().stream()
                    .anyMatch(existingJpa -> existingJpa.getId() != null && existingJpa.getId().equals(domainEntry.id()));

            if (!jpaExists && domainEntry.id() == null) {
                PortfolioEntryJpa newEntryJpa = new PortfolioEntryJpa();
                newEntryJpa.setId(null);
                newEntryJpa.setPortfolio(jpa);

                SkillJpa skillJpa = new SkillJpa();
                skillJpa.setId(domainEntry.skillId());
                newEntryJpa.setSkill(skillJpa);

                newEntryJpa.setSkillLevel(domainEntry.skillLevel());
                newEntryJpa.setExpirationDate(domainEntry.expirationDate());
                newEntryJpa.setNotes(domainEntry.notes());
                newEntryJpa.setVerificationStatus(domainEntry.verificationStatus());
                newEntryJpa.setVerifiedBy(domainEntry.verifiedBy());
                newEntryJpa.setVerifiedOn(domainEntry.verifiedOn());

                jpa.getPortfolioEntry().add(newEntryJpa);
            }
        }
    }
}
