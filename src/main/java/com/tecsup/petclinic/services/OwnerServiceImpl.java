package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        if (!ownerRepository.existsById(owner.getId())) {
            throw new OwnerNotFoundException("Owner not found with ID: " + owner.getId());
        }
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(id)) {
            throw new OwnerNotFoundException("Owner not found with ID: " + id);
        }
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Owner not found with ID: " + id));
    }

    @Override
    public List<Owner> findByName(String name) {
        return ownerRepository.findByNombres(name);
    }

    @Override
    public List<Owner> findByAddress(String address) {
        return ownerRepository.findByAddress(address);
    }

    @Override
    public List<Owner> findByTelephone(String telephone) {
        return ownerRepository.findByTelephone(telephone);
    }

    @Override
    public List<Owner> findByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
