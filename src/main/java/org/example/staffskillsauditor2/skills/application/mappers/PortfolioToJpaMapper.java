package org.example.staffskillsauditor2.skills.application.mappers;

import org.example.staffskillsauditor2.skills.domain.PortfolioEntry;
import org.example.staffskillsauditor2.skills.domain.SkillPortfolio;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class PortfolioToJpaMapper {

    public static void map(SkillPortfolio domain, PortfolioJpa jpa) {
        Objects.requireNonNull(domain, "Domain portfolio cannot be null");
        Objects.requireNonNull(jpa, "Jpa entity cannot be null");

        jpa.setId(domain.id().id());
        jpa.setStaffId(domain.staffId());

        List<PortfolioEntryJpa> toRemove = new ArrayList<>();

        for (PortfolioEntryJpa existingJpa : jpa.getPortfolioEntry()) {
            if (existingJpa.getId() != null) {
                PortfolioEntry matchingDomain = domain.entries().stream()
                        .filter(de -> de.id() != null && de.id().equals(existingJpa.getId()))
                        .findFirst()
                        .orElse(null);

                if (matchingDomain == null) {
                    toRemove.add(existingJpa);
                } else {
                    existingJpa.setSkillLevel(matchingDomain.skillLevel());
                    existingJpa.setExpirationDate(matchingDomain.expirationDate());
                    existingJpa.setNotes(matchingDomain.notes());
                    existingJpa.setVerificationStatus(matchingDomain.verificationStatus());
                    existingJpa.setVerifiedBy(matchingDomain.verifiedBy());
                    existingJpa.setVerifiedOn(matchingDomain.verifiedOn());
                }
            }
        }

        jpa.getPortfolioEntry().removeAll(toRemove);

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
