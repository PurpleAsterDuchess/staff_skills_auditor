package org.example.staffskillsauditor2.portfolio.domain.events;

import lombok.Getter;
import org.example.staffskillsauditor2.common.events.Event;
import org.example.staffskillsauditor2.common.events.LocalEvent;

import java.time.LocalDateTime;

@Getter
public class SkillVerifiedEvent implements LocalEvent {

    private final Long id;
    private final String portfolioId;
    private final String staffId;
    private final String skillId;
    private final String verifiedBy;
    private final LocalDateTime verifiedOn;

    public SkillVerifiedEvent(
            String portfolioId,
            String staffId,
            String skillId,
            String verifiedBy,
            LocalDateTime verifiedOn
    ) {
        this(null, portfolioId, staffId, skillId, verifiedBy, verifiedOn);
    }

    public SkillVerifiedEvent(
            Long id,
            String portfolioId,
            String staffId,
            String skillId,
            String verifiedBy,
            LocalDateTime verifiedOn
    ) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.staffId = staffId;
        this.skillId = skillId;
        this.verifiedBy = verifiedBy;
        this.verifiedOn = verifiedOn;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Event withId(Long id) {
        return new SkillVerifiedEvent(
                id,
                portfolioId,
                staffId,
                skillId,
                verifiedBy,
                verifiedOn
        );
    }
}