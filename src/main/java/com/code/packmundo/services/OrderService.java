package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.Order;
import com.code.packmundo.models.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        order.setUuid(UUID.randomUUID());
        order.setIntime(LocalDateTime.now());
        order.setUserId(1); //todo userId
        order.setStatus(Order.Status.WAITING.toString());
        return orderRepository.save(order);
    }

}