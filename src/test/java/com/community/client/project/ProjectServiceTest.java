package com.community.client.project;
import com.community.client.models.Community;
import com.community.client.models.Project;
import com.community.client.repositories.ProjectRepository;
import com.community.client.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    //mock a project repository and service
    @Mock
    private ProjectRepository mockProjectRepository;
    private ProjectService mockProjectService;

    @BeforeEach
    void setUp(){
        //mock a project object
        mockProjectService = new ProjectService(mockProjectRepository);
    }

    @Test
    void testSaveProject(){
        BigDecimal fundsRequired = new BigDecimal(1234);
        BigDecimal fundsCollected = new BigDecimal(4321);
        Community dummyCommunity = new Community();
        dummyCommunity.setId(123L);
        dummyCommunity.setName("Name");
        dummyCommunity.setDescription("The characteristics of someone or something");
        //given a dummy project
        Project dummyProject = new Project(1L,"test name","test description", fundsRequired,fundsCollected,"test.jpg",123L,dummyCommunity);
        //when
        mockProjectService.saveProject(dummyProject);
        //then
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        //verify the repository invoke the save method
        verify(mockProjectRepository).save(projectArgumentCaptor.capture());
        Project capturedProject = projectArgumentCaptor.getValue();
        //assert the repository was given the same object with what we pass in
        assertThat(capturedProject).isEqualTo(dummyProject);
        assertThat(capturedProject.getCommunity()).isEqualTo(dummyCommunity);
        assertThat(capturedProject.getCreatorUserId()).isEqualTo(123L);
    }

    @Test
    void testGetAllProjects(){
        //when
        mockProjectService.getAllProjects();
        //then
        verify(mockProjectRepository).findAll();  //Only to test if the service invoke findAll() in the repository
    }

    @Test
    void testGetProjectById(){
        //save a dummyProject first
        BigDecimal fundsRequired = new BigDecimal(1234);
        BigDecimal fundsCollected = new BigDecimal(4321);
        Community dummyCommunity = new Community();
        dummyCommunity.setId(123L);
        dummyCommunity.setName("Name");
        dummyCommunity.setDescription("The characteristics of someone or something");
        Project dummyProject = new Project(1L,"test name","test description", fundsRequired,fundsCollected,"test.jpg",123L,dummyCommunity);
        mockProjectService.saveProject(dummyProject);
        Optional<Project> ofResult = Optional.of(dummyProject);

        when(mockProjectRepository.findProjectById((Long) any())).thenReturn(ofResult);
        Project savedProject = mockProjectService.getProjectById(1L);
        assertThat(savedProject).isEqualTo(dummyProject);
        assertThat(savedProject.getName()).isEqualTo("test name");
        verify(mockProjectRepository).findProjectById(1L);
    }

    @Test
    void testGetProjectByIdWithNull() {
        when(mockProjectRepository.findProjectById((Long) any())).thenReturn(Optional.empty());
        assertNull(mockProjectService.getProjectById(1L));
        verify(mockProjectRepository).findProjectById((Long) any());
        assertTrue(mockProjectService.getAllProjects().isEmpty());
    }
}
