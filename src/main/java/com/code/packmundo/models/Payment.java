package com.code.packmundo.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "payments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    @Id
    @JsonIgnore
    private String transactionId;
    private int qouteId;
    private int deliveryQouteId;
    private int userId;
    private float amount;
    private String status;
    private LocalDateTime intime;

    public String getTransactionId() {
        return transactionId;
    }

    public int getQouteId() {
        return qouteId;
    }

    public int getDeliveryQouteId() {
        return deliveryQouteId;
    }

    public int getUserId() {
        return userId;
    }

    public float getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getIntime() {
        return intime;
    }

    public void setIntime(LocalDateTime intime) {
        this.intime = intime;
    }
    
}