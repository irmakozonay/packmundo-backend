package com.code.packmundo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_addresses")
public class UserAddress {

    @Id
    @JsonIgnore
    private int addressId;
    private int userId;
	private String type;

    public int getAddressId() {
        return addressId;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

}