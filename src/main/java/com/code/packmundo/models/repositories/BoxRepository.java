package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.Box;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoxRepository extends CrudRepository<Box, Integer> {
    @Query(value = "SELECT id FROM packmundo.boxes WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
    Iterable<Box> findByUserId(int userId);
}