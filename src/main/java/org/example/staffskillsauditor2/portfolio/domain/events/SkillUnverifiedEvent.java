package org.example.staffskillsauditor2.portfolio.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.Event;
import org.example.staffskillsauditor2.common.events.LocalEvent;

@Getter
public class SkillUnverifiedEvent implements LocalEvent {

    private final Long id;
    private final String portfolioId;
    private final String staffId;
    private final String skillId;

    public SkillUnverifiedEvent(
            String portfolioId,
            String staffId,
            String skillId
    ) {
        this(null, portfolioId, staffId, skillId);
    }

    public SkillUnverifiedEvent(
            Long id,
            String portfolioId,
            String staffId,
            String skillId
    ) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.staffId = staffId;
        this.skillId = skillId;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Event withId(Long id) {
        return new SkillUnverifiedEvent(
                id,
                portfolioId,
                staffId,
                skillId
        );
    }
}