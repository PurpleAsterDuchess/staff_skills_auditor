package org.example.staffskillsauditor2.portfolio.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.example.staffskillsauditor2.portfolio.persistance.entities.PortfolioJpa;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioJpa, String> {
    Optional<PortfolioJpa> findByStaffId(String staffId);
}
