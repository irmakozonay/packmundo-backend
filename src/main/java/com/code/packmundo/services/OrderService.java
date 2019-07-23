package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.Order;
import com.code.packmundo.models.Quote;
import com.code.packmundo.models.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BoxService boxService;
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, BoxService boxService, QuoteService quoteService, UserService userService) {
        this.orderRepository = orderRepository;
        this.boxService = boxService;
        this.quoteService = quoteService;
        this.userService = userService;
    }

    public Iterable<Order> getUserOrders() {
        Iterable<Order> userOrders = orderRepository.findByUserId(1); // todo userId
        for (Order order : userOrders) {
            order.setBox(boxService.getBox(order.getBoxId()));
            order.setAddress(userService.getAddress(order.getAddressId()));
        }
        return userOrders;
    }

    public Order createOrder(Order order) {
        order.setBoxId(boxService.getIdByUuid(order.getBox().getUuid()));
        order.setUuid(UUID.randomUUID());
        order.setIntime(LocalDateTime.now());
        //todo addressId, userId
        order.setStatus(Order.Status.WAITING_QUOTES.toString());
        return orderRepository.save(order);
    }

    public void addQuotes(UUID orderUuid, Iterable<Quote> quotes) {
        Order order = orderRepository.findByUuid(orderUuid);
        order.setStatus(Order.Status.QUOTES_READY.toString());
        orderRepository.save(order);
        quoteService.addQuotesForOrderId(order.getId(), quotes);
    }

    public Iterable<Quote> getQuotes(UUID orderUuidId) {
        int orderId = orderRepository.getIdByUuid(orderUuidId);
        return quoteService.getQuotesByOrderId(orderId);
    }

    public Iterable<Order> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.setBox(boxService.getBox(order.getBoxId()));
            order.setUser(userService.getUser(order.getUserId()));
            order.setAddress(userService.getAddress(order.getAddressId()));
        }
        return orders;
    }

}