package com.community.client.userobject;

import com.community.client.models.UserObject;
import com.community.client.repositories.UserObjectRepository;
import com.community.client.services.UserObjectService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserObjectTests {

    @Test
    public void testToCheckSaveUserObjectFunctionality() {
        //create a mock user object repository
        UserObjectRepository mockUserObjectRepository = mock(UserObjectRepository.class);

        //create a dummy user
        UserObject dummyUser = new UserObject(1L, "testuser", "test@test.com", "testpassword");

        //mock the repository save method
        when(mockUserObjectRepository.save(dummyUser)).thenReturn(dummyUser);

        //create the mock user object service
        UserObjectService mockUserObjectService = new UserObjectService(mockUserObjectRepository);

        //save that user to the mockUserRepository
        UserObject savedUser = mockUserObjectService.saveUser(dummyUser);

        //make assertions
        assertEquals("1L", savedUser.getId());
        assertEquals("testuser", savedUser.getName());
        assertEquals("test@test.com", savedUser.getEmail());
        assertEquals("testpassword", savedUser.getPassword());
    }

    //Test to get the User by its ID
    @Test
    public void testToGetAUserObjectById(){
        //create a mock user object repository
        UserObjectRepository mockUserObjectRepository = mock(UserObjectRepository.class);

        //create a dummy user
        UserObject dummyUser = new UserObject(1L, "testuser", "test@test.com", "testpassword");

        //mock the repository save method
        when(mockUserObjectRepository.findById(1L)).thenReturn(java.util.Optional.of(dummyUser));

        //create the mock user object service
        UserObjectService mockUserObjectService = new UserObjectService(mockUserObjectRepository);

        //save that user to the mockUserRepository
        mockUserObjectService.saveUser(dummyUser);

        //Now lets get the user by ID
        UserObject fetchedUser = mockUserObjectService.getUserById(1L);

        //make assertions
        assertEquals("testuser", fetchedUser.getName());
        assertEquals("test@test.com", fetchedUser.getEmail());
        assertEquals("testpassword", fetchedUser.getPassword());
    }

    //Test to get the User by its EMAIL
    @Test
    public void testToGetAUserObjectByEmail(){
        //create a mock user object repository
        UserObjectRepository mockUserObjectRepository = mock(UserObjectRepository.class);

        //create a dummy user
        UserObject dummyUser = new UserObject(1L, "testuser", "test@test.com", "testpassword");

        //mock the repository save method
        when(mockUserObjectRepository.findUserObjectByEmail("test@test.com")).thenReturn(java.util.Optional.of(dummyUser));

        //create the mock user object service
        UserObjectService mockUserObjectService = new UserObjectService(mockUserObjectRepository);

        //save that user to the mockUserRepository
        mockUserObjectService.saveUser(dummyUser);

        //Now lets get the user by ID
        UserObject fetchedUser = mockUserObjectService.getUserByEmail("test@test.com");

        //make assertions
        assertEquals("testuser", fetchedUser.getName());
        assertEquals("test@test.com", fetchedUser.getEmail());
        assertEquals("testpassword", fetchedUser.getPassword());
    }

}
