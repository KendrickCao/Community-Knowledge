package com.community.client.services;

import com.community.client.models.Address;
import com.community.client.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    public AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}