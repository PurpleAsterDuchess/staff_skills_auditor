package org.example.staffskillsauditor2.skills.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.LocalEvent;
import org.example.staffskillsauditor2.common.events.Event;

@Getter
public class SkillAllocatedEvent implements LocalEvent {
    private final Long id; // Database outbox ID [17]
    private final String portfolioId;
    private final String staffId;
    private final String skillId;
    private final int skillLevel;
    private final String notes;

    public SkillAllocatedEvent(String portfolioId, String staffId, String skillId, int skillLevel, String notes) {
        this(null, portfolioId, staffId, skillId, skillLevel, notes);
    }

    public SkillAllocatedEvent(Long id, String portfolioId, String staffId, String skillId, int skillLevel, String notes) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.staffId = staffId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
        this.notes = notes;
    }

    @Override
    public Long id() { return id; }

    @Override
    public Event withId(Long id) {
        return new SkillAllocatedEvent(id, this.portfolioId, this.staffId, this.skillId, this.skillLevel, this.notes);
    }
}