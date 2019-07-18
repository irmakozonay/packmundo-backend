package com.code.packmundo.models.repositories;

import com.code.packmundo.models.UserAddress;

import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {
    Iterable<UserAddress> findByUserId(int userId);
}