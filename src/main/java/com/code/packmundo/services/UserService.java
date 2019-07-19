package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.code.packmundo.models.Address;
import com.code.packmundo.models.User;
import com.code.packmundo.models.UserAddress;
import com.code.packmundo.models.repositories.AddressRepository;
import com.code.packmundo.models.repositories.UserAddressRepository;
import com.code.packmundo.models.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userAddressRepository = userAddressRepository;
    }

    public User saveUser(User user) {
        if (user.getUuid() == null) {
            user.setUuid(UUID.randomUUID());
            user.setIntime(LocalDateTime.now());
        } else {
            user.setId(userRepository.getIdByUuid(user.getUuid()));
        }
        return userRepository.save(user);
    }

    public User getUser(int userId) {
        return userRepository.findById(userId).get();
    }

    //address

    public Address addAddress(Address address) {
        address.setUuid(UUID.randomUUID());
        address.setIntime(LocalDateTime.now());
        address = addressRepository.save(address);
        UserAddress userAddress = new UserAddress(address.getId(), 1); //todo userId
        userAddressRepository.save(userAddress);
        return address;
    }

    public List<Address> getUserAddresses() {
        Iterable<UserAddress> userAddresses = userAddressRepository.findByUserId(1); //todo userId
        List<Address> addresses = new ArrayList<>();
        for (UserAddress userAddress : userAddresses) {
            addresses.add(addressRepository.findById(userAddress.getAddressId()).get());
        }
        return addresses;
    }

    public Address getAddress(int addressId) {
        return addressRepository.findById(addressId).get();
    }

}