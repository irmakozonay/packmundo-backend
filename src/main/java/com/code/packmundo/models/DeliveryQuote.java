package com.code.packmundo.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "delivery_quotes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int days;
    private float price;
    private String currency;
    @JsonIgnore
    private Integer deliveryCompanyId;
    @JsonIgnore
    private int quoteId;
    private UUID uuid;

    @Transient
    private DeliveryCompany deliveryCompany;

    public int getId() {
        return id;
    }

    public int getDays() {
        return days;
    }
    
    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getDeliveryCompanyId() {
        return deliveryCompanyId;
    }

    public void setDeliveryCompanyId(Integer deliveryCompanyId) {
        this.deliveryCompanyId = deliveryCompanyId;
    }
    
    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DeliveryCompany getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

}