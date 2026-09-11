package org.example.staffskillsauditor2.portfolio.application.mappers;

import org.example.staffskillsauditor2.portfolio.domain.PortfolioEntry;
import org.example.staffskillsauditor2.portfolio.domain.SkillPortfolio;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioEntryJpa;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PortfolioToJpaMapper {

    public static void map(SkillPortfolio domain, PortfolioJpa jpa) {
        Objects.requireNonNull(domain, "Domain portfolio cannot be null");
        Objects.requireNonNull(jpa, "Jpa entity cannot be null");

        jpa.setId(domain.id().id());
        jpa.setStaffId(domain.staffId());

        List<PortfolioEntryJpa> existingEntries = jpa.getPortfolioEntry();

        for (PortfolioEntry domainEntry : domain.entries()) {

            PortfolioEntryJpa existingJpa = existingEntries.stream()
                    .filter(jpaEntry ->
                            jpaEntry.getSkill() != null
                                    && jpaEntry.getSkill().getId() != null
                                    && jpaEntry.getSkill().getId().equals(domainEntry.skillId())
                    )
                    .findFirst()
                    .orElse(null);

            if (existingJpa != null) {
                existingJpa.setSkillLevel(domainEntry.skillLevel());
                existingJpa.setExpirationDate(domainEntry.expirationDate());
                existingJpa.setNotes(domainEntry.notes());
                existingJpa.setVerificationStatus(
                        domainEntry.verificationStatus().name()
                );
                existingJpa.setVerifiedBy(domainEntry.verifiedBy());
                existingJpa.setVerifiedOn(domainEntry.verifiedOn());

            } else {

                PortfolioEntryJpa newEntryJpa = new PortfolioEntryJpa();

                newEntryJpa.setPortfolio(jpa);

                SkillJpa skillJpa = new SkillJpa();
                skillJpa.setId(domainEntry.skillId());
                newEntryJpa.setSkill(skillJpa);

                newEntryJpa.setSkillLevel(domainEntry.skillLevel());
                newEntryJpa.setExpirationDate(domainEntry.expirationDate());
                newEntryJpa.setNotes(domainEntry.notes());
                newEntryJpa.setVerificationStatus(
                        domainEntry.verificationStatus().name()
                );
                newEntryJpa.setVerifiedBy(domainEntry.verifiedBy());
                newEntryJpa.setVerifiedOn(domainEntry.verifiedOn());

                existingEntries.add(newEntryJpa);
            }
        }

        /*
         * Remove JPA entries that no longer exist in the domain.
         *
         * This is done by skillId because it is the stable identifier
         * shared between the current domain and persistence models.
         */
        List<PortfolioEntryJpa> toRemove = new ArrayList<>();

        for (PortfolioEntryJpa existingJpa : existingEntries) {

            if (existingJpa.getSkill() == null
                    || existingJpa.getSkill().getId() == null) {
                continue;
            }

            boolean existsInDomain = domain.entries().stream()
                    .anyMatch(domainEntry ->
                            domainEntry.skillId().equals(
                                    existingJpa.getSkill().getId()
                            )
                    );

            if (!existsInDomain) {
                toRemove.add(existingJpa);
            }
        }

        existingEntries.removeAll(toRemove);
    }
}