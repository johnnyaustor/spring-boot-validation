package com.jap.springvalidation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jap.springvalidation.dto.request.PeopleRequest;
import com.jap.springvalidation.utils.PeopleStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.jap.springvalidation.ResponseBodyMatchers.responseBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostPeople_returnBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PeopleRequest.builder().build()));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPostPeople_withInvalidEmail() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("A")
                .email("agus").build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError("email", "must be a well-formed email address"));
    }

    @Test
    void testPostPeople_withConflictEmail() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("johnny")
                .email("john@email.com")
                .age(20).build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.statusCode").value(PeopleStatus.EMAIL_NOT_AVAILABLE.getStatusCode()));
    }

    @Test
    void testPostPeople_withAgeNull() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("Agus")
                .email("agus@jap.com").build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPostPeople_withAgeUnder() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("Agus")
                .email("agus@jap.com")
                .age(5).build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError("age", "must be greater than or equal to 17"));
    }

    @Test
    void testPostPeople_withAgeGreater() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("Agus")
                .email("agus@jap.com")
                .age(56).build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError("age", "must be less than or equal to 55"));
    }

    @Test
    void testPostPeople_returnOk() throws Exception {
        PeopleRequest request = PeopleRequest.builder()
                .fullName("Agus")
                .email("agus@jap.com")
                .age(20).build();

        MockHttpServletRequestBuilder requestBuilder = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

}
