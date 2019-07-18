package com.code.packmundo.controllers;

import java.util.List;

import com.code.packmundo.models.Address;
import com.code.packmundo.models.Order;
import com.code.packmundo.models.User;
import com.code.packmundo.models.UserBox;
import com.code.packmundo.services.BoxService;
import com.code.packmundo.services.OrderService;
import com.code.packmundo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final BoxService boxService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, BoxService boxService, OrderService orderService) {
        this.userService = userService;
        this.boxService = boxService;
        this.orderService = orderService;
    }

    @PostMapping(value = "update")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "boxes")
    public Iterable<UserBox> getUserBoxes() {
        return boxService.getUserBoxes();
    }

    @RequestMapping(value = "orders")
    public Iterable<Order> getUserOrders() {
        return orderService.getUserOrders();
    }

    //address

    @PostMapping(value = "address/add")
    public Address addAddress(@RequestBody Address address) {
        return userService.addAddress(address);
    }

    @RequestMapping(value = "addresses")
    public List<Address> getUserAddresses() {
        return userService.getUserAddresses();
    }

}