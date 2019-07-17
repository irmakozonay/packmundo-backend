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
@Table(name = "quotes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int companyId;
    private int orderId;
    private float price;
    private String currency;
    private LocalDateTime intime;
    private UUID uuid;

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getOrderId() {
        return orderId;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getIntime() {
        return intime;
    }

    public void setIntime(LocalDateTime intime) {
        this.intime = intime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}