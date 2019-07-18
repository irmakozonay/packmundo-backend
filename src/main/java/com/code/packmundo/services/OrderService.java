package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.DeliveryQuote;
import com.code.packmundo.models.Order;
import com.code.packmundo.models.Quote;
import com.code.packmundo.models.repositories.AddressRepository;
import com.code.packmundo.models.repositories.BoxRepository;
import com.code.packmundo.models.repositories.DeliveryQuoteRepository;
import com.code.packmundo.models.repositories.OrderRepository;
import com.code.packmundo.models.repositories.QuoteRepository;
import com.code.packmundo.models.repositories.UserRepository;
import com.code.packmundo.models.repositories.CompanyRepository;
import com.code.packmundo.models.repositories.DeliveryCompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BoxRepository boxRepository;
    private final QuoteRepository quoteRepository; //todo quoteservice
    private final DeliveryQuoteRepository deliveryQuoteRepository; //todo quoteservice
    private final CompanyRepository companyRepository; //todo companyservice
    private final DeliveryCompanyRepository deliveryCompanyRepository; //todo companyservice
    private final AddressRepository addressRepository; //todo userservice
    private final UserRepository userRepository; //todo userservice

    @Autowired
    public OrderService(OrderRepository orderRepository, BoxRepository boxRepository, QuoteRepository quoteRepository,
            DeliveryQuoteRepository deliveryQuoteRepository, CompanyRepository companyRepository, 
            DeliveryCompanyRepository deliveryCompanyRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.boxRepository = boxRepository;
        this.quoteRepository = quoteRepository;
        this.deliveryQuoteRepository = deliveryQuoteRepository;
        this.companyRepository = companyRepository;
        this.deliveryCompanyRepository = deliveryCompanyRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public Iterable<Order> getUserOrders() {
        Iterable<Order> userOrders = orderRepository.findByUserId(1); // todo userId
        for (Order userOrder : userOrders) {
            userOrder.setBox(boxRepository.findById(userOrder.getBoxId()).get());
            userOrder.setAddress(addressRepository.findById(userOrder.getAddressId()).get());
        }
        return userOrders;
    }

    public Order createOrder(Order order) {
        order.setUuid(UUID.randomUUID());
        order.setIntime(LocalDateTime.now());
        order.setUserId(1); // todo userId
        //todo addressId
        order.setStatus(Order.Status.WAITING.toString());
        return orderRepository.save(order);
    }

    public void addQuotes(UUID orderUuid, Iterable<Quote> quotes) {
        Order order = orderRepository.findByUuid(orderUuid);
        order.setStatus(Order.Status.QUOTES_READY.toString());
        orderRepository.save(order);
        for (Quote quote : quotes) {
            int companyId = companyRepository.getIdByUuid(quote.getCompany().getUuid());
            quote.setCompanyId(companyId);
            quote.setOrderId(order.getId());
            quote.setUuid(UUID.randomUUID());
            quote.setIntime(LocalDateTime.now());
            Quote resultQuote = quoteRepository.save(quote);
            for (DeliveryQuote deliveryQuote : quote.getDeliveryQuotes()) {
                int deliveryCompanyId = deliveryCompanyRepository.getIdByUuid(deliveryQuote.getDeliveryCompany().getUuid());
                deliveryQuote.setDeliveryCompanyId(deliveryCompanyId);
                deliveryQuote.setQuoteId(resultQuote.getId());
                deliveryQuote.setUuid(UUID.randomUUID());
                deliveryQuoteRepository.save(deliveryQuote);
            }
        }
    }

    public Iterable<Quote> getQuotes(UUID orderUuidId) {
        int orderId = orderRepository.getIdByUuid(orderUuidId);
        Iterable<Quote> quotes = quoteRepository.findByOrderId(orderId);
        for (Quote quote : quotes) {
            quote.setCompany(companyRepository.findById(quote.getCompanyId()).get());
            Iterable<DeliveryQuote> deliveryQuotes = deliveryQuoteRepository.findByQuoteId(quote.getId());
            for (DeliveryQuote deliveryQuote : deliveryQuotes) {
                deliveryQuote.setDeliveryCompany(deliveryCompanyRepository.findById(deliveryQuote.getDeliveryCompanyId()).get());
            }
            quote.setDeliveryQuotes(deliveryQuotes);
        }
        return quotes;
    }

    public Iterable<Order> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.setBox(boxRepository.findById(order.getBoxId()).get());
            order.setUser(userRepository.findById(order.getUserId()).get());
            order.setAddress(addressRepository.findById(order.getAddressId()).get());
        }
        return orders;
    }

}