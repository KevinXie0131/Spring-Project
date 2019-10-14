package com.test.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkPersonInfoWhenNameMissingNameThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/submit").param("age", "20");

        mockMvc.perform(createPerson).andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenNameTooShortThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/submit").param("name", "R")
                                                                               .param("age", "20");

        mockMvc.perform(createPerson).andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenAgeMissingThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/submit").param("name", "Rob");

        mockMvc.perform(createPerson).andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenAgeTooYoungThenFailure() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/submit").param("age", "1")
                                                                                .param("name", "Rob");

        mockMvc.perform(createPerson).andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenValidRequestThenSuccess() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/submit").param("name", "Rob")
                                                                                .param("age", "20");

        mockMvc.perform(createPerson).andExpect(model().hasNoErrors());
    }
}
