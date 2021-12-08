package com.community.client.services;

import com.community.client.models.Address;
import com.community.client.models.Community;
import com.community.client.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    public AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id){
        Optional<Address> addressOptional = addressRepository.findAddressById(id);
        if (addressOptional.isPresent()){
            return addressOptional.get();
        }else {
            return null;
        }
    }
}