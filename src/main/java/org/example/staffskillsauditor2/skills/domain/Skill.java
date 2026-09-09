package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.skills.application.exceptions.CannotBeBlankException;

import java.util.Objects;


public class Skill extends AggregateRoot<Skill> {
    private String name;
    private String description;
    private String category;
    private boolean isActive; // "ACTIVE" or "INACTIVE"

    public static final String IDENTITY_CANNOT_BE_NULL = "Skill identity cannot be null";
    public static final String NAME_CANNOT_BE_BLANK = "Skill name cannot be blank";
    public static final String CATEGORY_CANNOT_BE_BLANK = "Skill category cannot be blank";

    private Skill(Identity<Skill> id, String name, String description, String category, boolean isActive) {
        super(Objects.requireNonNull(id, IDENTITY_CANNOT_BE_NULL));

        if (name == null || name.isBlank()) {
            throw new CannotBeBlankException(NAME_CANNOT_BE_BLANK);
        }
        if (category == null || category.isBlank()) {
            throw new CannotBeBlankException(CATEGORY_CANNOT_BE_BLANK);
        }

        this.name = name.trim();
        this.description = description != null ? description.trim() : "";
        this.category = category.trim();
        this.isActive = isActive;
    }

    public static Skill createNewSkill(Identity<Skill> id, String name, String description, String category) {
        return new Skill(id, name, description, category, true);
    }

    public static Skill reconstitute(Identity<Skill> id, String name, String description, String category, Boolean isActive) {
        return new Skill(id, name, description, category, true);
    }

    public void editSkill(String newName, String newDescription, String newCategory) {
        if (newName == null || newName.isBlank()) {
            throw new CannotBeBlankException(NAME_CANNOT_BE_BLANK);
        }
        if (newCategory == null || newCategory.isBlank()) {
            throw new CannotBeBlankException(CATEGORY_CANNOT_BE_BLANK);
        }

        this.name = newName.trim();
        this.description = newDescription != null ? newDescription.trim() : "";
        this.category = newCategory.trim();
    }

    public void deactivateSkill() {
        this.isActive = false;
    }

    public void activateSkill() {
        this.isActive = true;
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

    public Boolean isActive() {
        return isActive;
    }
}
