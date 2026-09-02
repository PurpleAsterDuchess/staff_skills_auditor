package org.example.staffskillsauditor2.skills.domain;

import org.example.staffskillsauditor2.common.domain.IdentifiedValueObject;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PortfolioEntry implements IdentifiedValueObject {
    private final Integer id;
    private final String skillId;
    private final int skillLevel;
    private final LocalDate expirationDate;
    private final String notes;
    private String verificationStatus;
    private final String verifiedBy;
    private final LocalDateTime verifiedOn;

    public PortfolioEntry(String skillId, int skillLevel, String notes) {
        this(null, skillId, skillLevel, null, notes, "PENDING", null, null);
    }

    public PortfolioEntry(Integer id, String skillId, int skillLevel, LocalDate expirationDate,
                          String notes, String verificationStatus, String verifiedBy, LocalDateTime verifiedOn) {
        this.id = id;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
        this.expirationDate = expirationDate;
        this.notes = notes != null ? notes.trim() : "";
        this.verificationStatus = verificationStatus != null ? verificationStatus : "PENDING";
        this.verifiedBy = verifiedBy;
        this.verifiedOn = verifiedOn;
    }

    public Integer id() {
        return id;
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

    public String verificationStatus() {
        return verificationStatus;
    }

    public String verifiedBy() {
        return verifiedBy;
    }

    public LocalDateTime verifiedOn() {
        return verifiedOn;
    }

    public void verify() {
        this.verificationStatus = "VERIFIED";
    }
}
