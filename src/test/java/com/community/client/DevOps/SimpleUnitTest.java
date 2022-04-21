package com.community.client.DevOps;

import com.community.client.models.Community;
import com.community.client.models.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleUnitTest {
    @Autowired
    private MockMvc mockMvc;
    // Unit tests using a mock MVC

    @Test
    void ProjectDTOTest() {
        BigDecimal fundsRequired = new BigDecimal(1234);
        BigDecimal fundsCollected = new BigDecimal(4321);
        Community testCommunity = new Community(1L,"TEST COMMUNITY","TTTTT","COMMUNITY image");
        Project testProject = new Project(1L,"test-name","test-description", fundsRequired,fundsCollected,"i.jpg",1L,testCommunity);
        assertEquals(1L,testProject.getId());
        assertEquals("test-name",testProject.getName());
        assertEquals("test-description",testProject.getDescription());
    }

    @Test
    public void ShowLoginPageTest() throws Exception {
        this.mockMvc.perform(get("/Login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Need an Account?")));

    }


}

