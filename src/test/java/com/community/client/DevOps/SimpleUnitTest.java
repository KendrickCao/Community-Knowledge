package com.community.client.DevOps;

import com.community.client.models.Community;
import com.community.client.models.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SimpleUnitTest {

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

}

