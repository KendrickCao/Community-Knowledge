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

/**
 * Represents the tests for assessing functionality associated with the Event object
 * including: saving an event, retrieving a specific event, and retrieving an event
 * set by community or project id. Each test is split up into a step-by-step method to
 * aid understanding of why a test is failing and at which point it is doing so. Mockito
 * is used to mock the external dependencies used to achieve the event, community,
 * and project database functionality.
 */
public class EventTests {

    /*
     * To reduce code duplication and to create more concise test cases some
     * local variables used by more than one test were converted into private fields.
     */
    private final EventRepository mockEventRepository = mock(EventRepository.class);
    private final EventService mockEventService = new EventService(mockEventRepository);
    private final Community dummyCommunity = new Community (1L);
    private final Project dummyProject = new Project (2L);
    private final Event dummyEvent = new Event(3L, dummyCommunity, dummyProject,"20/12/2021",
            "Winter Reads","CF14 4TY", "Borrow winter themed books",
            "Sandra Hail, John Smith","1", "image.jpg");
    private final Set<Event> event = new HashSet<>();
    private final Event dummyEvent1 = new Event();
    private final Event dummyEvent2 = new Event();


    /**
     * Test to mock the save event functionality using a mock event repository and service.
     */
    @Test
    void whenEventIsCreatedSaveEvent() {

        // Mock the event repository save method
        when(mockEventRepository.save(dummyEvent)).thenReturn(dummyEvent);

        // Save the event using the mock event service
        Event savedEvent = mockEventService.saveEvent(dummyEvent);

        // Make assertions
        assertEquals(3, savedEvent.getId());
        assertEquals(1, savedEvent.getCommunity().getId());
        assertEquals(2, savedEvent.getProject().getId());
        assertEquals("20/12/2021", savedEvent.getDate());
        assertEquals("Winter Reads", savedEvent.getName());
        assertEquals("CF14 4TY", savedEvent.getAddress());
        assertEquals("Borrow winter themed books", savedEvent.getAboutSection());
        assertEquals("Sandra Hail, John Smith", savedEvent.getContributors());
        assertEquals("1", savedEvent.getCreatorUserId());
        assertEquals("image.jpg", savedEvent.getEventImage());
    }

    /**
     * Test to mock the get event by id functionality using a mock event repository and service.
     */
    @Test
    void whenRetrievingEventByIdReturnEvent(){

        // Mock the event repository findEventById method
        when(mockEventRepository.findEventById(3L)).thenReturn(java.util.Optional.of(dummyEvent));

        // Get the event by its id using the mock event service
        Event fetchedEvent = mockEventService.getEventById(3L);

        // Make assertions
        assertEquals(1, fetchedEvent.getCommunity().getId());
        assertEquals(2, fetchedEvent.getProject().getId());
        assertEquals("20/12/2021", fetchedEvent.getDate());
        assertEquals("Winter Reads", fetchedEvent.getName());
        assertEquals("CF14 4TY", fetchedEvent.getAddress());
        assertEquals("Borrow winter themed books", fetchedEvent.getAboutSection());
        assertEquals("Sandra Hail, John Smith", fetchedEvent.getContributors());
        assertEquals("1", fetchedEvent.getCreatorUserId());
        assertEquals("image.jpg", fetchedEvent.getEventImage());
    }

    /**
     * Test to mock the get events by community functionality using the mock community
     * and event repositories and services. Multiple dummy events were created
     * to verify that more than one event could be associated with one community.
     */
    @Test
    void WhenRetrievingCommunityEventSetReturnEvents(){

        // Create mock community repository
        CommunityRepository mockCommunityRepository = mock(CommunityRepository.class);

        // Create mock community service
        CommunityService mockCommunityService = new CommunityService(mockCommunityRepository);

        // Assign event set to dummy community
        dummyCommunity.setEvent(event);

        // Mock the community repository save method
        when(mockCommunityRepository.save(dummyCommunity)).thenReturn(dummyCommunity);

        // Save the community using the mock community service
        Community savedCommunity = mockCommunityService.saveCommunity(dummyCommunity);

        // Set event id for event objects
        dummyEvent1.setId(2L);
        dummyEvent2.setId(3L);

        // Mock the event repository save method
        when(mockEventRepository.save(dummyEvent1)).thenReturn(dummyEvent1);
        when(mockEventRepository.save(dummyEvent2)).thenReturn(dummyEvent2);

        // Save the events using the mock event service
        Event savedEvent1 = mockEventService.saveEvent(dummyEvent1);
        Event savedEvent2 = mockEventService.saveEvent(dummyEvent2);

        // Get event set associated with dummy community
        Set<Event> communityEventSet = savedCommunity.getEvent();

        // Add events to dummy community event set
        communityEventSet.add(savedEvent1);
        communityEventSet.add(savedEvent2);

        // Assign community to dummy events
        savedEvent1.setCommunity(savedCommunity);
        savedEvent2.setCommunity(savedCommunity);

        // Make assertions
        assertEquals(2, savedCommunity.getEvent().size());
        assertEquals(1, savedEvent1.getCommunity().getId());
        assertEquals(1, savedEvent2.getCommunity().getId());
        assertEquals(2, savedEvent1.getId());
        assertEquals(3, savedEvent2.getId());
    }

    /**
     * Test to mock the get events by project functionality using the mock project
     * and event repositories and services. Multiple dummy events were created
     * to verify that more than one event could be associated with one project.
     */
    @Test
    void WhenRetrievingProjectEventSetReturnEvents(){

        // Create mock project repository
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);

        // Create mock project service
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);

        // Assign event set to dummy project
        dummyProject.setEvent(event);

        // Mock the project repository save method
        when(mockProjectRepository.save(dummyProject)).thenReturn(dummyProject);

        // Save the project using the mock project service
        Project savedProject = mockProjectService.saveProject(dummyProject);

        // Set event id for event objects
        dummyEvent1.setId(2L);
        dummyEvent2.setId(3L);

        // Mock the event repository save method
        when(mockEventRepository.save(dummyEvent1)).thenReturn(dummyEvent1);
        when(mockEventRepository.save(dummyEvent2)).thenReturn(dummyEvent2);

        // Save the events using the mock event service
        Event savedEvent1 = mockEventService.saveEvent(dummyEvent1);
        Event savedEvent2 = mockEventService.saveEvent(dummyEvent2);

        // Get event set associated with dummy project
        Set<Event> ProjectEventSet = savedProject.getEvent();

        // Add events to dummy project event set
        ProjectEventSet.add(savedEvent1);
        ProjectEventSet.add(savedEvent2);

        // Assign project to dummy events
        savedEvent1.setProject(savedProject);
        savedEvent2.setProject(savedProject);

        // Make assertions
        assertEquals(2, savedProject.getEvent().size());
        assertEquals(2, savedEvent1.getProject().getId());
        assertEquals(2, savedEvent2.getProject().getId());
        assertEquals(2, savedEvent1.getId());
        assertEquals(3, savedEvent2.getId());
    }
}
