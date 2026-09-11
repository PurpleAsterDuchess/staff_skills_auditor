package org.example.staffskillsauditor2.portfolio.persistance.repositories;

import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioEntryJpa;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PortfolioEntryRepository extends
        CrudRepository<PortfolioEntryJpa, Integer>,
        JpaSpecificationExecutor<PortfolioEntryJpa> {
    List<PortfolioEntryJpa> findByVerificationStatus(String status);
    List<PortfolioEntryJpa> findByExpirationDateBefore(LocalDate date);
}
