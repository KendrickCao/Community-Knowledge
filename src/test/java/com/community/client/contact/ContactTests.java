package com.community.client.contact;

import com.community.client.models.Contact;
import com.community.client.repositories.ContactRepository;
import com.community.client.services.ContactService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ContactTests {
    //Running a 'Mock Test' to save Contact Form submission through its functionality using a mock   contact from repository, service and model
    @Test
    public void testCheckToSaveContactFunctionality(){
        // Creating a mock Contact repository
        ContactRepository mockContactRepository = mock(ContactRepository.class);
        //Create a mock Contact Service
        ContactService mockContactService = new ContactService(mockContactRepository);

        // Creating the Contact Object
        Contact mockContact = new Contact();
        mockContact.setId(1L);
        mockContact.setName("TestOne");
        mockContact.setEmail("TestOne@gmail.com");
        mockContact.setPhone("07344989987");
        mockContact.setMessage("I'm doing a test");
        mockContact.setUploadInput("image.png");

        // Save method by mocking the Contact repository using mokito function
        when(mockContactRepository.save(mockContact)).thenReturn(mockContact);

        // Storing the Contact to the mockContactRepository
        Contact savedContact = mockContactService.saveContact(mockContact);

        //Making assertion
        assertEquals(1L, savedContact.getId());
        assertEquals("TestOne", savedContact.getName());
        assertEquals("TestOne@gmail.com", savedContact.getEmail());
        assertEquals("07344989987", savedContact.getPhone());
        assertEquals("I'm doing a test", savedContact.getMessage());
        assertEquals("image.png", savedContact.getUploadInput());
    }
}
