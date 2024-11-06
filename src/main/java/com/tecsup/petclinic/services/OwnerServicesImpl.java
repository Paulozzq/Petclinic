package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OwnerServicesImpl implements OwnerServices {

    @Autowired
    private final OwnerRepository ownerRepository;

    public OwnerServicesImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteById(Integer id) {
        ownerRepository.deleteById(id);
    }

}
