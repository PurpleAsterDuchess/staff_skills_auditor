package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.example.staffskillsauditor2.skills.persistance.entities.PortfolioJpa;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioJpa, String> {
}