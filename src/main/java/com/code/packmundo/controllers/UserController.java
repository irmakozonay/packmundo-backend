package com.code.packmundo.controllers;

import com.code.packmundo.models.Order;
import com.code.packmundo.models.UserBox;
import com.code.packmundo.services.BoxService;
import com.code.packmundo.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final BoxService boxService;
    private final OrderService orderService;

    @Autowired
    public UserController(BoxService boxService, OrderService orderService) {
        this.boxService = boxService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "boxes")
    public Iterable<UserBox> getUserBoxes() {
        return boxService.getUserBoxes();
    }

    @RequestMapping(value = "orders")
    public Iterable<Order> getUserOrders() {
        return orderService.getUserOrders();
    }

}