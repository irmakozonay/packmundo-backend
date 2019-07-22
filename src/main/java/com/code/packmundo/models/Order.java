package com.code.packmundo.models;

import java.time.LocalDateTime;
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
@Table(name = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    public enum Status {
        WAITING("WAITING"), QUOTES_READY("QUOTES_READY"), PAYMENT_RECEIVED("PAYMENT_RECEIVED");

        private String text;
 
        Status(String text) {
            this.text = text;
        }
    
        public String toString() {
            return text;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @JsonIgnore
    private int boxId;
    @JsonIgnore
    private int userId;
    private int quantity;
    @JsonIgnore
    private int addressId;
    private String status;
    private LocalDateTime intime;
    private UUID uuid;

    @Transient
    private Box box;
    @Transient
    private User user;
    @Transient
    private Address address;

    public int getId() {
        return id;
    }

    public int getBoxId() {
        return boxId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setStatus(String status) {
        this.status = status;
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

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}