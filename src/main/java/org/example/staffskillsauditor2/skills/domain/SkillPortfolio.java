package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.domain.events.SkillAllocatedEvent;
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

    public static SkillPortfolio portfolioOfWithEvent(Identity<SkillPortfolio> id, String staffId) {
        SkillPortfolio portfolio = new SkillPortfolio(id, staffId, new ArrayList<>());
        return portfolio;
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
}