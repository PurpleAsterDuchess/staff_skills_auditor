package org.example.staffskillsauditor2.skills.persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.staffskillsauditor2.skills.application.dto.PortfolioEntryDTO;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "skill_portfolio")
@Table(name = "skill_portfolio")
@Getter
@Setter
@ToString
public class PortfolioJpa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "staff_id", nullable = false, unique = true)
    private String staffId;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortfolioEntryJpa> portfolioEntry = new ArrayList<>();
}