package org.example.staffskillsauditor2.skills.persistance.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "portfolioEntry")
@Table(name = "portfolio_entry")
@Getter
@Setter
@ToString
public class PortfolioEntryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    @ToString.Exclude
    private PortfolioJpa portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillJpa skill;

    @Column(name = "skill_level", nullable = false)
    private Integer skillLevel;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "verification_status", nullable = false, length = 20)
    private String verificationStatus;

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "verified_on")
    private LocalDateTime verifiedOn;
}
