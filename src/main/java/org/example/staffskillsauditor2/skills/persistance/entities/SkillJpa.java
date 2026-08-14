package org.example.staffskillsauditor2.skills.persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "skill")
@Table(name = "skill_aggregate")
@Getter
@Setter
@ToString
public class SkillJpa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}