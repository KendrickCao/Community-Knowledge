package com.community.client.community;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Set;
import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.repositories.CommunityRepository;
import com.community.client.repositories.UserObjectRepository;
import com.community.client.services.CommunityService;
import com.community.client.services.UserObjectService;
import org.junit.jupiter.api.Test;


//Test block to handle all the tests related to community
public class CommunityModelTests {

    //Events and Projects if attached to the community, needs to be tested separately 
    @Test
    public void addCommunityTestWithoutProjectAndEvents(){
        
       UserObjectRepository userObjectRepository = mock(UserObjectRepository.class);
        CommunityRepository communityRepository = mock(CommunityRepository.class);

        //create a  mock service for user object
        UserObjectService userObjectService = new UserObjectService(userObjectRepository);

        //create a mock community service using the above mock community repository
        CommunityService communityService = new CommunityService(communityRepository);

        //Create a dummy User
        UserObject dummyUserObject = new UserObject();
        dummyUserObject.setEmail("test-user@test.com");
        dummyUserObject.setId(1L);
        dummyUserObject.setName("test user");
        dummyUserObject.setPassword("12345678");

        //Setting the when and return mockito functions to the repository
        when(userObjectRepository.save(dummyUserObject)).thenReturn(dummyUserObject);
        //Doing the save using the service layer which in turn will use the mocked repo layer
        UserObject savedUser = userObjectService.saveUser(dummyUserObject);

        //create a Dummy Community Object
        Community dummyCommunity = new Community();
        dummyCommunity.setName("Test Community");
        dummyCommunity.setId(1L);
        dummyCommunity.setDescription("Test community description");
        dummyCommunity.setCommunityImage("communityImage.jpg");
        //Setting the when and return mockito functions to the repository
        when(communityRepository.save(dummyCommunity)).thenReturn(dummyCommunity);
        //Doing the save using the service layer which in turn will use the mocked repo layer
        Community savedCommunity = communityService.saveCommunity(dummyCommunity);

        //Now we add this community to the community set of the user
        Set<Community> communitiesOfSavedUser = savedUser.getCommunitySet();
        communitiesOfSavedUser.add(savedCommunity);
        userObjectService.saveUser(savedUser);

        //Making the asertions
        assertEquals(1L, savedCommunity.getId() );
        assertEquals("Test Community", savedCommunity.getName());
        assertEquals("Test community description", savedCommunity.getDescription());

    }

    //Test to get a community BY ID
    @Test
    public void testCaseToCheckGetCommunityById(){
        
        CommunityRepository communityRepository = mock(CommunityRepository.class);

        //create a Dummy Community Object
        Community dummyCommunity = new Community(1L, "Test Community","Test community description", "communityImage.jpg" );
    
        when(communityRepository.findCommunityById(1L)).thenReturn(java.util.Optional.of(dummyCommunity));

        //create a mock community service using the above mock community repository
        CommunityService communityService = new CommunityService(communityRepository);
        //find community by ID
        Community communityFound = communityService.getCommunityById(1L);

        //make the assertions
        assertEquals(1, communityFound.getId());
        assertEquals("Test Community", communityFound.getName());
        assertEquals("Test community description", communityFound.getDescription());
        assertEquals("communityImage.jpg", communityFound.getCommunityImage());
    }
    
}
