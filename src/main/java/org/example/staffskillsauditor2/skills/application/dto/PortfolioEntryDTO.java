package org.example.staffskillsauditor2.skills.application.dto;

import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;
import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PortfolioEntryDTO(
        Integer id,
        String portfolio_id,
        String skill_id,
        Integer skill_level,
        LocalDate expiration_date,
        String notes,
        String verification_status,
        String verified_by,
        LocalDateTime verified_on
) {}
