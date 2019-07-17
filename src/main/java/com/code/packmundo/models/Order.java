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
@Table(name = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int boxId;
    private int userId;
    private int quantity;
    private int addressId;
    private String status;
    private LocalDateTime intime;
    private UUID uuid;

    public int getId() {
        return id;
    }

    public int getBoxId() {
        return boxId;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAddressId() {
        return addressId;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    

}