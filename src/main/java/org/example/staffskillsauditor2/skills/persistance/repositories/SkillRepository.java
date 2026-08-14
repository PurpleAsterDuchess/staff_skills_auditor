package org.example.staffskillsauditor2.skills.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.example.staffskillsauditor2.skills.persistance.entities.SkillJpa;

@Repository
public interface SkillRepository extends CrudRepository<SkillJpa, String> {
}