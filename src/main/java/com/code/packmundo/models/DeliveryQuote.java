package com.code.packmundo.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String speed;
    private LocalDateTime date1;
    private LocalDateTime date2;
    private float price;
    private String currency;
    private int deliveryCompanyId;
    private int quotesId;
    private UUID uuid;

    public int getId() {
        return id;
    }

    public String getSpeed() {
        return speed;
    }

    public LocalDateTime getDate1() {
        return date1;
    }

    public LocalDateTime getDate2() {
        return date2;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getDeliveryCompanyId() {
        return deliveryCompanyId;
    }

    public int getQuotesId() {
        return quotesId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}