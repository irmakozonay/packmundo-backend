package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.Address;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    @Query(value = "SELECT id FROM packmundo.addresses WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
}