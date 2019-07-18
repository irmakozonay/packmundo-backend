package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query(value = "SELECT id FROM packmundo.orders WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
    Order findByUuid(UUID uuid);
    Iterable<Order> findByUserId(int userId);
}