package com.code.packmundo.models.repositories;

import com.code.packmundo.models.BoxType;

import org.springframework.data.repository.CrudRepository;

public interface BoxTypeRepository extends CrudRepository<BoxType, Integer> {
    Iterable<BoxType> findByMainTypeIdIsNull();
    Iterable<BoxType> findByMainTypeId(int mainTypeId);
}