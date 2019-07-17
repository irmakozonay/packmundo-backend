package com.code.packmundo.models.repositories;

import com.code.packmundo.models.Quote;

import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
}