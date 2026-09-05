package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PortfolioEntryRepository extends CrudRepository<PortfolioEntryJpa, Integer> {
    List<PortfolioEntryJpa> findByVerificationStatus(String status);
    List<PortfolioEntryJpa> findByExpirationDateBefore(LocalDate date);
}
