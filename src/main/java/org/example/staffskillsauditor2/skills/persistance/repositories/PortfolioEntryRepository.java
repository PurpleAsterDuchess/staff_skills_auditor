package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioEntryJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioEntryRepository extends CrudRepository<PortfolioEntryJpa, String> {

}
