package com.community.client.Event;

import com.community.client.models.Community;
import com.community.client.models.Event;
import com.community.client.models.Project;
import com.community.client.repositories.CommunityRepository;
import com.community.client.repositories.EventRepository;
import com.community.client.repositories.ProjectRepository;
import com.community.client.services.CommunityService;
import com.community.client.services.EventService;
import com.community.client.services.ProjectService;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventTests {

    //Test to mock the save event functionality using a mock event repository, service and model.
    @Test
    public void testToCheckSaveEventFunctionality() {
        // Create mock event,community and project repositories
        CommunityRepository mockCommunityRepository = mock(CommunityRepository.class);
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);
        EventRepository mockEventRepository = mock(EventRepository.class);

        // Create Mock event, community and project services
        CommunityService mockCommunityService = new CommunityService(mockCommunityRepository);
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);
        EventService mockEventService = new EventService(mockEventRepository);

        // Create community, project and event objects
        Community mockCommunity = new Community (1L);
        Project mockProject = new Project (1L);
        Event mockEvent = new Event(1L,mockCommunity,mockProject,"20/12/2021",
                "Winter Reads","CF14 4TY", "Borrow winter themed books",
                "Sandra Hail, John Smith","1", "image.jpg");


        // Mock the repository save method
        when(mockEventRepository.save(mockEvent)).thenReturn(mockEvent);


        // Save that event to the mockEventRepository
        Event savedEvent = mockEventService.saveEvent(mockEvent);

        // Make assertions
        assertEquals(1, savedEvent.getId());
        assertEquals(1, savedEvent.getCommunity().getId());
        assertEquals(1, savedEvent.getProject().getId());
        assertEquals("20/12/2021", savedEvent.getDate());
        assertEquals("Winter Reads", savedEvent.getName());
        assertEquals("CF14 4TY", savedEvent.getAddress());
        assertEquals("Borrow winter themed books", savedEvent.getAboutSection());
        assertEquals("Sandra Hail, John Smith", savedEvent.getContributors());
        assertEquals("1", savedEvent.getCreatorUserId());
        assertEquals("image.jpg", savedEvent.getEventImage());
    }

    // Test to get event by its Id.
    @Test
    public void testToGetEventById(){

        // Create mock event,community and project repositories
        CommunityRepository mockCommunityRepository = mock(CommunityRepository.class);
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);
        EventRepository mockEventRepository = mock(EventRepository.class);

        // Create Mock event, community and project services
        CommunityService mockCommunityService = new CommunityService(mockCommunityRepository);
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);
        EventService mockEventService = new EventService(mockEventRepository);

        // Create community, project and event objects
        Community mockCommunity = new Community (1L);
        Project mockProject = new Project (1L);
        Event mockEvent = new Event(1L, mockCommunity,mockProject,"20/12/2021",
                "Winter Reads","CF14 4TY", "Borrow winter themed books",
                "Sandra Hail, John Smith","1", "image.jpg");

        // Mock the repository save method
        when(mockEventRepository.findEventById(1L)).thenReturn(java.util.Optional.of(mockEvent));

        // Save that event to the mockEventRepository
        mockEventService.saveEvent(mockEvent);

        // Get the event by Id
        Event fetchedEvent = mockEventService.getEventById(1L);

        // Make assertions
        assertEquals(1, fetchedEvent.getCommunity().getId());
        assertEquals(1, fetchedEvent.getProject().getId());
        assertEquals("20/12/2021", fetchedEvent.getDate());
        assertEquals("Winter Reads", fetchedEvent.getName());
        assertEquals("CF14 4TY", fetchedEvent.getAddress());
        assertEquals("Borrow winter themed books", fetchedEvent.getAboutSection());
        assertEquals("Sandra Hail, John Smith", fetchedEvent.getContributors());
        assertEquals("1", fetchedEvent.getCreatorUserId());
        assertEquals("image.jpg", fetchedEvent.getEventImage());
    }

    // Test to get events by community
    @Test
    public void testToGetEventsByCommunity(){

        // Create mock event and community repositories
        CommunityRepository mockCommunityRepository = mock(CommunityRepository.class);
        EventRepository mockEventRepository = mock(EventRepository.class);

        // Create mock event and community services
        CommunityService mockCommunityService = new CommunityService(mockCommunityRepository);
        EventService mockEventService = new EventService(mockEventRepository);

        Community mockCommunity1 = new Community ();
        mockCommunity1.setId(1L);
        Set<Event> event = new HashSet<>();
        mockCommunity1.setEvent(event);

        when(mockCommunityRepository.save(mockCommunity1)).thenReturn(mockCommunity1);

        Community savedCommunity = mockCommunityService.saveCommunity(mockCommunity1);

        Set<Event> communityEventSet = savedCommunity.getEvent();

        Event mockEvent1 = new Event();
        mockEvent1.setId(2L);
        mockEvent1.setName("test-event 1");

        when(mockEventRepository.save(mockEvent1)).thenReturn(mockEvent1);
        Event savedEvent1 = mockEventService.saveEvent(mockEvent1);
        communityEventSet.add(savedEvent1);

        savedEvent1.setCommunity(savedCommunity);

        assertEquals(1, savedCommunity.getEvent().size());
        assertEquals(1, savedEvent1.getCommunity().getId());
        assertEquals(2, savedEvent1.getId());

    }

    // Test to get events by project
    @Test
    public void testToGetEventsByProject(){

        // Create mock event and project repositories
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);
        EventRepository mockEventRepository = mock(EventRepository.class);

        // Create mock event and project services
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);
        EventService mockEventService = new EventService(mockEventRepository);

        Project mockProject1 = new Project ();
        mockProject1.setId(1L);
        Set<Event> event = new HashSet<>();
        mockProject1.setEvent(event);

        when(mockProjectRepository.save(mockProject1)).thenReturn(mockProject1);

        Project savedProject = mockProjectService.saveProject(mockProject1);

        Set<Event> ProjectEventSet = savedProject.getEvent();

        Event mockEvent1 = new Event();
        mockEvent1.setId(2L);
        mockEvent1.setName("test-event 1");

        when(mockEventRepository.save(mockEvent1)).thenReturn(mockEvent1);
        Event savedEvent1 = mockEventService.saveEvent(mockEvent1);
        ProjectEventSet.add(savedEvent1);

        savedEvent1.setProject(savedProject);

        assertEquals(1, savedProject.getEvent().size());
        assertEquals(1, savedEvent1.getProject().getId());
        assertEquals(2, savedEvent1.getId());

    }
}
