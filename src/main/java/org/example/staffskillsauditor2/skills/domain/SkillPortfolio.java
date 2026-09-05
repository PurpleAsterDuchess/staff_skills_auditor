package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.events.SkillAllocatedEvent;
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
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
        PortfolioEntry existingEntry = entries.stream()
                .filter(e -> e.skillId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Skill not found in this portfolio"));

        entries.remove(existingEntry);
        PortfolioEntry updatedEntry = new PortfolioEntry(
                existingEntry.id(),
                skillId,
                level,
                existingEntry.expirationDate(),
                notes,
                "PENDING",
                null,
                null
        );
        entries.add(updatedEntry);
        this.addDomainEvent(new SkillAllocatedEvent(this.id().id(), staffId, skillId, level, notes));
    }

    public void verifySkill(String skillId, String verifiedBy) {
        PortfolioEntry existingEntry = entries.stream()
                .filter(e -> e.skillId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Skill not found in this portfolio"));

        entries.remove(existingEntry);
        PortfolioEntry verifiedEntry = new PortfolioEntry(
                existingEntry.id(),
                skillId,
                existingEntry.skillLevel(),
                existingEntry.expirationDate(),
                existingEntry.notes(),
                "VERIFIED",
                verifiedBy,
                LocalDateTime.now()
        );
        entries.add(verifiedEntry);
    }
}