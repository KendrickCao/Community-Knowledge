package com.community.client.DevOps;

import com.community.client.controllers.MainController;
import com.community.client.models.Community;
import com.community.client.models.Project;
import com.community.client.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
public class LightweightMockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;
    @MockBean
    private EventService eventService;
    @MockBean
    private UserObjectService userObjectService;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private ContactService contactService;
    @MockBean
    private AdminViewAboutService adminViewAboutService;

    // Simple Unit tests using a mockMVC - no change here from container testing as no access to database
    @Test
    public void testProjectPage() throws Exception {
        this.mockMvc.perform(get("/projects")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Search projects...")));
    }

    //  Component test using a mockMVC but mocking the repository response (Note the @MockBean)
    //  This only tests the controller and the view layers
    @Test
    public void getProjectsTest() throws Exception {
        BigDecimal fundsRequired = new BigDecimal(1234);
        BigDecimal fundsCollected = new BigDecimal(4321);
        Community testCommunity = new Community(1L,"TEST COMMUNITY","TTTTT","COMMUNITY image");
        Project testProject = new Project(1L,"test-name","test-description", fundsRequired,fundsCollected,"i.jpg",1L,testCommunity);

        // This will return what the service would have returned - a set of ProjectDto called method in the repo
        given(this.projectService.getAllProjects()).willReturn(Set.of(testProject));

        this.mockMvc.perform(get("/projects")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test-name")));
    }
}
