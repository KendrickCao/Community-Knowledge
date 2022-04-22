package com.community.client.DevOps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class FullContainerMVCSecurityTests {

    @Autowired
    private MockMvc mockMvc;

//    Tests the post controller for login
//    and will check it returns the response with a JSON of userObject.
//    This is similar to the Lightweight test but it will test the repo / mysql database access.
    @Test
    public void testLoginApi() throws Exception {
        this.mockMvc.perform(post("/api/login-user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"email\": \"test@gmail.com\", \"password\": \"12345678\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test@gmail.com")));
    }
//    Tests the security control for project-create page, user cannot access this page without login(cookie)
//    and will check it returns the project-create page with a repo created response.
    @Test
    public void showCreateProjectPage() throws Exception {
        Cookie userName = new Cookie("userEmail","test@gmail.com");
        Cookie userEmail = new Cookie("userName","c21106784");
        this.mockMvc.perform(get("/project-create")
                .cookie(userName,userEmail))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create Project")));
    }
}
