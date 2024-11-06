package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    /**
     * Get all owners
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OwnerTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        log.info("owners: " + owners);
        owners.forEach(item -> log.info("Owner >>  {} ", item));

        List<OwnerTO> ownersTO = this.ownerMapper.toOwnerTOList(owners);
        log.info("ownersTO: " + ownersTO);
        ownersTO.forEach(item -> log.info("OwnerTO >>  {} ", item));

        return ResponseEntity.ok(ownersTO);
    }

    /**
     * Create owner
     *
     * @param ownerTO
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {
        Owner newOwner = this.ownerMapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = this.ownerMapper.toOwnerTO(ownerService.create(newOwner));

        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    /**
     * Find owner by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        OwnerTO ownerTO = null;

        try {
            Owner owner = ownerService.findById(id);
            ownerTO = this.ownerMapper.toOwnerTO(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ownerTO);
    }

    /**
     * Update and create owner
     *
     * @param ownerTO
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {
        OwnerTO updateOwnerTO = null;

        try {
            Owner updateOwner = ownerService.findById(id);
            updateOwner.setNombres(ownerTO.getNombres());
            updateOwner.setAddress(ownerTO.getAddress());
            updateOwner.setTelephone(ownerTO.getTelephone());
            updateOwner.setEmail(ownerTO.getEmail());

            ownerService.update(updateOwner);

            updateOwnerTO = this.ownerMapper.toOwnerTO(updateOwner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateOwnerTO);
    }

    /**
     * Delete owner by id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Delete ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
