package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import java.util.Objects;


public class Skill extends AggregateRoot<Skill> {
    private String name;
    private String description;
    private String category;
    private String status; // "ACTIVE" or "INACTIVE"

    private Skill(Identity<Skill> id, String name, String description, String category, String status) {
        super(id);
        this.name = Objects.requireNonNull(name, "Skill name is required");
        this.description = description != null ? description.trim() : "";
        this.category = Objects.requireNonNull(category, "Skill category is required");
        this.status = Objects.requireNonNull(status, "Skill status is required");
    }

    public static Skill createNewSkill(Identity<Skill> id, String name, String description, String category) {
        return new Skill(id, name, description, category, "ACTIVE");
    }

    public static Skill reconstitute(Identity<Skill> id, String name, String description, String category, String status) {
        return new Skill(id, name, description, category, status);
    }

    public void editSkill(String newName, String newDescription, String newCategory) {
        this.name = Objects.requireNonNull(newName, "Skill name cannot be null");
        this.description = newDescription != null ? newDescription.trim() : "";
        this.category = Objects.requireNonNull(newCategory, "Skill category cannot be null");
    }

    public void deactivateSkill() {
        this.status = "INACTIVE";
    }

    public void activateSkill() {
        this.status = "ACTIVE";
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String category() {
        return category;
    }

    public String status() {
        return status;
    }
}
