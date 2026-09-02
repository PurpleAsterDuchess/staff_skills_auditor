package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.Entity;
import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;

public class SkillAggregate extends AggregateRoot<SkillAggregate> {
    private String name;
    private String description;
    private String category;
    private boolean isActive;

    public SkillAggregate(Identity<SkillAggregate> id, String name, String description, String category, boolean isActive) {
        super(id);
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Skill name cannot be empty");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Skill category cannot be empty");

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