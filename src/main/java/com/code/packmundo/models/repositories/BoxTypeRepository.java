package com.code.packmundo.models.repositories;

import com.code.packmundo.models.BoxType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoxTypeRepository extends CrudRepository<BoxType, Integer> {
    Iterable<BoxType> findByMainTypeIdIsNull();
    Iterable<BoxType> findByMainTypeId(int mainTypeId);
    @Query(value = "SELECT fields_json FROM packmundo.box_types WHERE id = ?1", nativeQuery = true)
    String getFieldsById(int id);
}