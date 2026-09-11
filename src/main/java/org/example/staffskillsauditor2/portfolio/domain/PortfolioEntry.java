package org.example.staffskillsauditor2.portfolio.domain;

import org.example.staffskillsauditor2.common.domain.AggregateRoot;
import org.example.staffskillsauditor2.common.domain.Identity;
import org.example.staffskillsauditor2.portfolio.domain.VerificationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PortfolioEntry extends AggregateRoot<PortfolioEntry> {
    private final String skillId;
    private int skillLevel;
    private final LocalDate expirationDate;
    private String notes;
    private VerificationStatus verificationStatus;
    private String verifiedBy;
    private LocalDateTime verifiedOn;

    public PortfolioEntry(String skillId, int skillLevel, String notes) {
        this(
                Identity.generateId(),
                skillId,
                skillLevel,
                null,
                notes,
                VerificationStatus.PENDING,
                null,
                null
        );
    }

    public PortfolioEntry(
            Identity<PortfolioEntry> id,
            String skillId,
            int skillLevel,
            LocalDate expirationDate,
            String notes,
            VerificationStatus verificationStatus,
            String verifiedBy,
            LocalDateTime verifiedOn
    ) {
        super(id);

        if (skillId == null || skillId.isBlank()) {
            throw new IllegalArgumentException("Skill ID cannot be blank");
        }

        if (skillLevel < 1 || skillLevel > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }

        this.skillId = skillId;
        this.skillLevel = skillLevel;
        this.expirationDate = expirationDate;
        this.notes = notes != null ? notes.trim() : "";
        this.verificationStatus = verificationStatus != null
                ? verificationStatus
                : VerificationStatus.PENDING;
        this.verifiedBy = verifiedBy;
        this.verifiedOn = verifiedOn;
    }

    public Identity<PortfolioEntry> id() {
        return super.id();
    }

    public String skillId() {
        return skillId;
    }

    public int skillLevel() {
        return skillLevel;
    }

    public LocalDate expirationDate() {
        return expirationDate;
    }

    public String notes() {
        return notes;
    }

    public VerificationStatus verificationStatus() {
        return verificationStatus;
    }

    public String verifiedBy() {
        return verifiedBy;
    }

    public LocalDateTime verifiedOn() {
        return verifiedOn;
    }

    public void edit(int skillLevel, String notes) {
        if (skillLevel < 1 || skillLevel > 5) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }

        this.skillLevel = skillLevel;
        this.notes = notes != null ? notes.trim() : "";

        this.verificationStatus = VerificationStatus.PENDING;
        this.verifiedBy = null;
        this.verifiedOn = null;
    }

    public void verify(String verifiedBy, LocalDateTime verifiedOn) {
        if (verifiedBy == null || verifiedBy.isBlank()) {
            throw new IllegalArgumentException("Verifier cannot be blank");
        }

        this.verificationStatus = VerificationStatus.VERIFIED;
        this.verifiedBy = verifiedBy;
        this.verifiedOn = Objects.requireNonNull(
                verifiedOn,
                "Verification date cannot be null"
        );
    }

    public void unverify() {
        this.verificationStatus = VerificationStatus.PENDING;
        this.verifiedBy = null;
        this.verifiedOn = null;
    }

    public void reject(String rejectedBy, LocalDateTime rejectedOn) {
        if (rejectedBy == null || rejectedBy.isBlank()) {
            throw new IllegalArgumentException("Rejector cannot be blank");
        }

        this.verificationStatus = VerificationStatus.REJECTED;
        this.verifiedBy = rejectedBy;
        this.verifiedOn = Objects.requireNonNull(
                rejectedOn,
                "Rejection date cannot be null"
        );
    }
}
