package org.example.staffskillsauditor2.portfolio.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.Event;
import org.example.staffskillsauditor2.common.events.LocalEvent;

import java.time.LocalDateTime;

@Getter
public class SkillRejectedEvent implements LocalEvent {

    private final Long id;
    private final String portfolioId;
    private final String staffId;
    private final String skillId;
    private final String rejectedBy;
    private final LocalDateTime rejectedOn;

    public SkillRejectedEvent(
            String portfolioId,
            String staffId,
            String skillId,
            String rejectedBy,
            LocalDateTime rejectedOn
    ) {
        this(null, portfolioId, staffId, skillId, rejectedBy, rejectedOn);
    }

    public SkillRejectedEvent(
            Long id,
            String portfolioId,
            String staffId,
            String skillId,
            String rejectedBy,
            LocalDateTime rejectedOn
    ) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.staffId = staffId;
        this.skillId = skillId;
        this.rejectedBy = rejectedBy;
        this.rejectedOn = rejectedOn;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Event withId(Long id) {
        return new SkillRejectedEvent(
                id,
                portfolioId,
                staffId,
                skillId,
                rejectedBy,
                rejectedOn
        );
    }
}