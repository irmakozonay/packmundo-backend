package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.DeliveryQuote;
import com.code.packmundo.models.Quote;
import com.code.packmundo.models.repositories.DeliveryQuoteRepository;
import com.code.packmundo.models.repositories.QuoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final DeliveryQuoteRepository deliveryQuoteRepository;
    private final CompanyService companyService;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, DeliveryQuoteRepository deliveryQuoteRepository, CompanyService companyService) {
        this.quoteRepository = quoteRepository;
        this.deliveryQuoteRepository = deliveryQuoteRepository;
        this.companyService = companyService;
    }

    public Iterable<Quote> getQuotesByOrderId(int orderId) {
        Iterable<Quote> quotes = quoteRepository.findByOrderId(orderId);
        for (Quote quote : quotes) {
            quote.setCompany(companyService.getCompany(quote.getCompanyId()));
            Iterable<DeliveryQuote> deliveryQuotes = deliveryQuoteRepository.findByQuoteId(quote.getId());
            for (DeliveryQuote deliveryQuote : deliveryQuotes) {
                if (deliveryQuote.getDeliveryCompanyId() != null) {
                    deliveryQuote.setDeliveryCompany(companyService.getDeliveryCompany(deliveryQuote.getDeliveryCompanyId()));
                }
            }
            quote.setDeliveryQuotes(deliveryQuotes);
        }
        return quotes;
    }

    public void addQuotesForOrderId(int orderId, Iterable<Quote> quotes) {
        for (Quote quote : quotes) {
            int companyId = companyService.getCompanyIdByUuid(quote.getCompany().getUuid());
            quote.setCompanyId(companyId);
            quote.setOrderId(orderId);
            quote.setUuid(UUID.randomUUID());
            quote.setIntime(LocalDateTime.now());
            Quote resultQuote = quoteRepository.save(quote);
            for (DeliveryQuote deliveryQuote : quote.getDeliveryQuotes()) {
                if (deliveryQuote.getDeliveryCompany() != null) {
                    int deliveryCompanyId = companyService.getDeliveryCompanyIdByUuid(deliveryQuote.getDeliveryCompany().getUuid());
                    deliveryQuote.setDeliveryCompanyId(deliveryCompanyId);
                }
                deliveryQuote.setQuoteId(resultQuote.getId());
                deliveryQuote.setUuid(UUID.randomUUID());
                deliveryQuoteRepository.save(deliveryQuote);
            }
        }
    }

}