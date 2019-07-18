package com.code.packmundo.controllers;

import java.util.UUID;

import com.code.packmundo.models.Order;
import com.code.packmundo.models.Quote;
import com.code.packmundo.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    //qoute

    @PostMapping(value = "/quote/add")
    public void addQuotes(@RequestParam(value = "orderid") UUID orderUuidId, @RequestBody Iterable<Quote> quotes) {
        orderService.addQuotes(orderUuidId, quotes);
    }

}