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

//Test block to handle all the tests related to address
public class AddressTests {

    //testing save address functionality
    @Test
    public void testToCheckSaveAddressFunctionality(){
        //mock an addressResponsitory
        AddressRepository mockAddressRepository = mock(AddressRepository.class);

        //setting a dummy address
        Address dummyAddress = new Address(1l,"5 queen court","queen street","cardiff","cf232ed","uk");

        // Setting the when and return mockito functions to the repository
        when(mockAddressRepository.save(dummyAddress)).thenReturn(dummyAddress);

        // create a mock service for address
        AddressService mockAddressService = new AddressService(mockAddressRepository);

        // Doing the save address using the service layer which in turn will use the mocked repo layer
        Address saveAddress = mockAddressService.saveAddress(dummyAddress);

        // Making the assertions
        assertEquals(1l,saveAddress.getId());
        assertEquals("5 queen court", saveAddress.getLine1Address());
        assertEquals("queen street", saveAddress.getLine2Address());
        assertEquals("cardiff", saveAddress.getCity());
        assertEquals("cf232ed", saveAddress.getPostcode());
        assertEquals("uk", saveAddress.getCountry());
    }

    //testing get address by ID  functionality
    @Test
    public void testCaseToCheckGetAddressById() {
        //mock an addressResponsitory
        AddressRepository addressRepository = mock(AddressRepository.class);
        //setting a dummy address
        Address dummyAddress = new Address(1L, "test lineoneaddress", "test linetwoaddress", "test city", "test postcode",
                "test country");
        
        // Setting the when and return mockito functions to the repository
        when(addressRepository.findAddressById(1L)).thenReturn(java.util.Optional.of(dummyAddress));

        // create a mock service for address
        AddressService addressService = new AddressService(addressRepository);

         // Doing the get address using the service layer which in turn will use the mocked repo layer
        Address addressFound = addressService.getAddressById(1L);

        // Making the assertions
        assertEquals(1, addressFound.getId());
        assertEquals("test lineoneaddress", addressFound.getLine1Address());
        assertEquals("test linetwoaddress", addressFound.getLine2Address());
        assertEquals("test city", addressFound.getCity());
        assertEquals("test postcode", addressFound.getPostcode());
        assertEquals("test country", addressFound.getCountry());
    }
}
