package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.DeliveryCompany;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryCompanyRepository extends CrudRepository<DeliveryCompany, Integer> {
    @Query(value = "SELECT id FROM packmundo.delivery_companies WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
}