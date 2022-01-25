package com.community.client.userprofile;

import com.community.client.models.Address;
import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.models.UserProfile;
import com.community.client.repositories.AddressRepository;
import com.community.client.repositories.CommunityRepository;
import com.community.client.repositories.UserObjectRepository;
import com.community.client.repositories.UserProfileRepository;
import com.community.client.requests.UserProfileRequest;
import com.community.client.services.AddressService;
import com.community.client.services.CommunityService;
import com.community.client.services.UserObjectService;
import com.community.client.services.UserProfileService;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTests {

    //test to update the user profile
    @Test
    public void createUserProfileTest() {
        //mock a userProfileRepository
        UserProfileRepository mockUserProfileRepository = mock(UserProfileRepository.class);
        UserProfileRequest mockUserProfileRequest = mock(UserProfileRequest.class);

        //create a dummy address
        Address dummyAddress = new Address(1L,"test lineoneaddress","test linetwoaddress","test city","test postcode",
                "test country");
        AddressRepository mockAddressRepository = mock(AddressRepository.class);
        when(mockAddressRepository.save(dummyAddress)).thenReturn(dummyAddress);

        //create a dummy UserObject
        UserObject dummyUserObject = new UserObject(1L, "testuser", "test@test.com", "testpassword");
        UserObjectRepository mockUserObjectRepository = mock(UserObjectRepository.class);
        when(mockUserObjectRepository.save(dummyUserObject)).thenReturn((dummyUserObject));

        //create a dummy UserProfile
        UserProfile dummyUserProfile = new UserProfile();

        //set the attributes of the dummy userprofile
        dummyUserProfile.setId(1L);
        dummyUserProfile.setAboutYourSelf("test aboutyourself");
        dummyUserProfile.setQualifications("test qualifications");
        dummyUserProfile.setHobbies("test hobbies");
        dummyUserProfile.setAchievements("test achievements");
        dummyUserProfile.setPhone(123456789);
        dummyUserProfile.setAddress(dummyAddress);
        dummyUserProfile.setUserObject(dummyUserObject);

        // Setting the when and return mockito functions to the repository
        when(mockUserProfileRepository.save(dummyUserProfile)).thenReturn(dummyUserProfile);

        // create a mock userprofile service using the mock userprofile repository
        UserProfileService mockUserProfileService = new UserProfileService(mockUserProfileRepository);

        // Doing the save userprofile using the service layer which in turn will use the mocked repo layer
        UserProfile savedUserProfile = mockUserProfileService.saveProfile(dummyUserProfile);

        // Making the assertions
        assertEquals(1L, savedUserProfile.getId());
        assertEquals("test aboutyourself", savedUserProfile.getAboutYourSelf());
        assertEquals("test qualifications", savedUserProfile.getQualifications());
        assertEquals("test hobbies", savedUserProfile.getHobbies());
        assertEquals("test achievements", savedUserProfile.getAchievements());
        assertEquals(123456789, savedUserProfile.getPhone());
        assertEquals(dummyAddress, savedUserProfile.getAddress());
        assertEquals(dummyUserObject, savedUserProfile.getUserObject());

    }
}
