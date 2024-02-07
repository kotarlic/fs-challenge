package io.chatty.chatservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chatty.chatservice.dto.UserDto;
import io.chatty.chatservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    private final String RESOURCE_ENDPOINT = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void getOrCreateUser_returnUser_whenUsernameIsProvided() throws Exception {
        // Given
        UserDto testUser = UserDto.builder().username("Test user").build();

        when(userService.getOrCreateUser(any())).thenReturn(testUser);

        // When & Then
        mockMvc.perform(post(RESOURCE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(testUser.getUsername()));
    }
}
