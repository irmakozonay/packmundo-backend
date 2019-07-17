package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT id FROM packmundo.users WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
}