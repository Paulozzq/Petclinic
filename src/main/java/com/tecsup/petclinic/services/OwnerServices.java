package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;

public interface OwnerServices {

    Owner save(Owner owner);

    void deleteById(Integer id);
}
