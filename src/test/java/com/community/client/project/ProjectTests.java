package com.community.client.project;
import com.community.client.models.Project;
import com.community.client.repositories.ProjectRepository;
import com.community.client.services.ProjectService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ProjectTests {
    @Test
    public void testSaveProject(){
        //create a mock project object repository
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);
        BigDecimal fundsRequired = new BigDecimal(1234);
        BigDecimal fundsCollected = new BigDecimal(4321);

        //create a dummy project
        Project dummyProject = new Project(1L,"test name","test description", fundsRequired,fundsCollected,"test.jpg");

        //mock the repository save method
        when(mockProjectRepository.save(dummyProject)).thenReturn(dummyProject);

        //create the mock user object service
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);

        //save that user to the mockUserRepository
        Project savedProject = mockProjectService.saveProject(dummyProject);

        //make assertions
        assertEquals(1L, savedProject.getId());
        assertEquals("test name", savedProject.getName());
        assertEquals("test description", savedProject.getDescription());
        assertEquals(fundsRequired, savedProject.getFundsRequired());
        assertEquals(fundsCollected, savedProject.getFundsCollected());
        assertEquals("test.jpg", savedProject.getProjectCoverImage());

    }

}
