package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findById(Integer id);
}
