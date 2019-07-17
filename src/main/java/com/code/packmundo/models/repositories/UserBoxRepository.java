package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.UserBox;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserBoxRepository extends CrudRepository<UserBox, Integer> {
    @Query(value = "SELECT id FROM packmundo.user_boxes WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
}