package com.code.packmundo.models.repositories;

import com.code.packmundo.models.Payment;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}