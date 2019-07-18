package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.DeliveryQuote;
import com.code.packmundo.models.Order;
import com.code.packmundo.models.Quote;
import com.code.packmundo.models.repositories.BoxRepository;
import com.code.packmundo.models.repositories.DeliveryQuoteRepository;
import com.code.packmundo.models.repositories.OrderRepository;
import com.code.packmundo.models.repositories.QuoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BoxRepository boxRepository;
    private final QuoteRepository quoteRepository;
    private final DeliveryQuoteRepository deliveryQuoteRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, BoxRepository boxRepository, QuoteRepository quoteRepository, DeliveryQuoteRepository deliveryQuoteRepository) {
        this.orderRepository = orderRepository;
        this.boxRepository = boxRepository;
        this.quoteRepository = quoteRepository;
        this.deliveryQuoteRepository = deliveryQuoteRepository;
    }

    public Iterable<Order> getUserOrders(){
        Iterable<Order> userOrders = orderRepository.findByUserId(1); //todo userId
        for (Order userOrder : userOrders) {
            userOrder.setBox(boxRepository.findById(userOrder.getBoxId()).get());
        }
        return userOrders;
    }

    public Order createOrder(Order order) {
        order.setUuid(UUID.randomUUID());
        order.setIntime(LocalDateTime.now());
        order.setUserId(1); //todo userId
        order.setStatus(Order.Status.WAITING.toString());
        return orderRepository.save(order);
    }

    public void addQuotes(UUID orderUuidId, Iterable<Quote> quotes){
        int orderId = orderRepository.getIdByUuid(orderUuidId);
        for (Quote quote: quotes) { //todo companyId uuid olarak gelmiyor
            quote.setOrderId(orderId);
            quote.setUuid(UUID.randomUUID());
            quote.setIntime(LocalDateTime.now());
            Quote resultQuote = quoteRepository.save(quote);
            for (DeliveryQuote deliveryQuote : quote.getDeliveryQuotes()) { //todo delivery_companyId uuid olarak gelmiyor
                deliveryQuote.setQuoteId(resultQuote.getId());
                deliveryQuote.setUuid(UUID.randomUUID());
                deliveryQuoteRepository.save(deliveryQuote);
            }
        }
    }

}