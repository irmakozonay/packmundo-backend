package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.Company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    @Query(value = "SELECT id FROM packmundo.companies WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
}