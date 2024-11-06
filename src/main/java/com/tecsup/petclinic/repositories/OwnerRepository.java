package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    List<Owner> findByNombres(String nombres);

    List<Owner> findByAddress(String address);

    List<Owner> findByTelephone(String telephone);

    List<Owner> findByEmail(String email);

    @Override
    List<Owner> findAll();
}
