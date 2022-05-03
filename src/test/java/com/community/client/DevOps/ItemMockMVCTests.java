package com.community.client.DevOps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemMockMVCTests {

    @Autowired
    private MockMvc mockMvc;

    // Unit tests using a mock MVC
    @Test
    public void showLoginPageTest() throws Exception {
        this.mockMvc.perform(get("/Login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Need an Account?")));
    }

    //    Component test using a mock MVC but a test database
    @Test
    public void getProjectsTest() throws Exception {
        this.mockMvc.perform(get("/communities")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Wales Community")));
    }
}
