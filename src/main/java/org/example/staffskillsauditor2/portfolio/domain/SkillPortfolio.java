package org.example.staffskillsauditor2.portfolio.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.portfolio.domain.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.portfolio.domain.events.SkillAllocatedEvent;
import org.example.staffskillsauditor2.portfolio.domain.events.SkillRejectedEvent;
import org.example.staffskillsauditor2.portfolio.domain.events.SkillUnverifiedEvent;
import org.example.staffskillsauditor2.portfolio.domain.events.SkillVerifiedEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillPortfolio extends AggregateRoot<SkillPortfolio> {
    private final String staffId;
    private final List<PortfolioEntry> entries;

    private SkillPortfolio(Identity<SkillPortfolio> id, String staffId, List<PortfolioEntry> entries) {
        super(id);
        this.staffId = staffId;
        this.entries = entries != null ? entries : new ArrayList<>();
    }

    public static SkillPortfolio portfolioOf(Identity<SkillPortfolio> id, String staffId, List<PortfolioEntry> entries) {
        return new SkillPortfolio(id, staffId, entries);
    }


    public void allocateSkill(String skillId, int level, String notes) {
        boolean alreadyAllocated = entries.stream().anyMatch(e -> e.skillId().equals(skillId));
        if (alreadyAllocated) {
            throw new IllegalArgumentException("Skill already exists in this portfolio");
        }
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }

        this.entries.add(new PortfolioEntry(skillId, level, notes));

        this.addDomainEvent(new SkillAllocatedEvent(this.id().id(), staffId, skillId, level, notes));
    }

    public String staffId() { return staffId; }
    public List<PortfolioEntry> entries() { return Collections.unmodifiableList(entries); }
    public void editSkill(String skillId, int level, String notes) {
        PortfolioEntry existingEntry = findEntry(skillId);

        existingEntry.edit(level, notes);

        this.addDomainEvent(
                new SkillAllocatedEvent(
                        this.id().id(),
                        staffId,
                        skillId,
                        level,
                        notes
                )
        );
    }

    public void verifySkill(String skillId, String verifiedBy) {
        PortfolioEntry existingEntry = findEntry(skillId);
        LocalDateTime verifiedOn = LocalDateTime.now();

        existingEntry.verify(verifiedBy, verifiedOn);

        this.addDomainEvent(
                new SkillVerifiedEvent(
                        this.id().id(),
                        staffId,
                        skillId,
                        verifiedBy,
                        verifiedOn
                )
        );
    }

    public void unverifySkill(String skillId) {
        PortfolioEntry existingEntry = findEntry(skillId);

        existingEntry.unverify();

        this.addDomainEvent(
                new SkillUnverifiedEvent(
                        this.id().id(),
                        staffId,
                        skillId
                )
        );
    }

    public void rejectSkill(String skillId, String rejectedBy) {
        PortfolioEntry existingEntry = findEntry(skillId);

        LocalDateTime rejectedOn = LocalDateTime.now();

        existingEntry.reject(
                rejectedBy,
                rejectedOn
        );

        this.addDomainEvent(
                new SkillRejectedEvent(
                        this.id().id(),
                        staffId,
                        skillId,
                        rejectedBy,
                        rejectedOn
                )
        );
    }

    private PortfolioEntry findEntry(String skillId) {
        return entries.stream()
                .filter(e -> e.skillId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new SkillNotFoundException(skillId));
    }
}