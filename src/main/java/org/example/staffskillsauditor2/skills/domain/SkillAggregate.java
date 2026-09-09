package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.Entity;
import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.application.exceptions.CannotBeBlankException;

public class SkillAggregate extends AggregateRoot<SkillAggregate> {
    private String name;
    private String description;
    private String category;
    private boolean isActive;

    public static final String NAME_CANNOT_BE_BLANK = "Skill name cannot be blank";
    public static final String CATEGORY_CANNOT_BE_BLANK = "Skill category cannot be blank";

    public SkillAggregate(Identity<SkillAggregate> id, String name, String description, String category, boolean isActive) {
        super(id);
        if (name == null || name.isBlank()) throw new CannotBeBlankException(NAME_CANNOT_BE_BLANK);
        if (category == null || category.isBlank()) throw new CannotBeBlankException(CATEGORY_CANNOT_BE_BLANK);

        this.name = name.trim();
        this.description = description != null ? description.trim() : "";
        this.category = category.trim();
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public String name() { return name; }
    public String description() { return description; }
    public String category() { return category; }
    public boolean isActive() { return isActive; }
}