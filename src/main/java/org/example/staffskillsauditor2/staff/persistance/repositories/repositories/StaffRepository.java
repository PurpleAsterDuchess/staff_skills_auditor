package org.example.staffskillsauditor2.staff.persistance.repositories.repositories;

import org.example.staffskillsauditor2.staff.persistance.entities.StaffJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<StaffJpa, String> {
}
