package org.apache.spring.dubbo.consumer.user.application.web;

import org.apache.spring.dubbo.consumer.SpringBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
class UserControllerTest extends SpringBaseTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void loginUserInputTest() throws Exception {
        this.mockMvc.perform(post("/login").param("email", "admin@example.com").param("password", "123"))
                .andExpect(view().name("homepage"));
        this.mockMvc.perform(post("/login").param("email", "admin@example.com").param("password", "1234"))
                .andExpect(view().name("login"));
    }
}