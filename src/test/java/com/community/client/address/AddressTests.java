package com.community.client.address;

import com.community.client.models.Address;
import com.community.client.models.Community;
import com.community.client.repositories.AddressRepository;
import com.community.client.repositories.CommunityRepository;
import com.community.client.services.AddressService;
import com.community.client.services.CommunityService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressTests {

    @Test
    public void testToCheckSaveAddressFunctionality(){
        AddressRepository mockAddressRepository = mock(AddressRepository.class);
        Address dummyAddress = new Address(1l,"5 queen court","queen street","cardiff","cf232ed","uk");
        when(mockAddressRepository.save(dummyAddress)).thenReturn(dummyAddress);
        AddressService mockAddressService = new AddressService(mockAddressRepository);
        Address saveAddress = mockAddressService.saveAddress(dummyAddress);

        assertEquals(1l,saveAddress.getId());
        assertEquals("5 queen court", saveAddress.getLine1Address());
        assertEquals("queen street", saveAddress.getLine2Address());
        assertEquals("cardiff", saveAddress.getCity());
        assertEquals("cf232ed", saveAddress.getPostcode());
        assertEquals("uk", saveAddress.getCountry());
    }

    @Test
    public void testCaseToCheckGetAddressById() {

        AddressRepository addressRepository = mock(AddressRepository.class);

        Address dummyAddress = new Address(1L, "test lineoneaddress", "test linetwoaddress", "test city", "test postcode",
                "test country");

        when(addressRepository.findAddressById(1L)).thenReturn(java.util.Optional.of(dummyAddress));

        AddressService addressService = new AddressService(addressRepository);
        Address addressFound = addressService.getAddressById(1L);

        assertEquals(1, addressFound.getId());
        assertEquals("test lineoneaddress", addressFound.getLine1Address());
        assertEquals("test linetwoaddress", addressFound.getLine2Address());
        assertEquals("test city", addressFound.getCity());
        assertEquals("test postcode", addressFound.getPostcode());
        assertEquals("test country", addressFound.getCountry());
    }
}
