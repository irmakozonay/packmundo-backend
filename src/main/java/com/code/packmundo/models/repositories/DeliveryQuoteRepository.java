package com.code.packmundo.models.repositories;

import java.util.UUID;

import com.code.packmundo.models.DeliveryQuote;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryQuoteRepository extends CrudRepository<DeliveryQuote, Integer> {
    @Query(value = "SELECT id FROM packmundo.delivery_quotes WHERE uuid = ?1", nativeQuery = true)
    int getIdByUuid(UUID uuid);
    Iterable<DeliveryQuote> findByQuoteId(int quoteId);
}