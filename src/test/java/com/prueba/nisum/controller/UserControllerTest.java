package com.prueba.nisum.controller;

import com.prueba.nisum.dto.UserRequest;
import com.prueba.nisum.dto.UserResponse;
import com.prueba.nisum.model.User;
import com.prueba.nisum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void registerUserTest() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Jaime Che");
        userRequest.setEmail("jaime@gmail.com");
        userRequest.setPassword("password123");

        UserResponse userResponse = new UserResponse();
        userResponse.setName("Jaime Che");
        userResponse.setToken("dummy-token");
        userResponse.setActive(true);

        when(userService.registerUser(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jaime Che\",\"email\":\"jaime@gmail.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jaime Che"))
                .andExpect(jsonPath("$.token").value("dummy-token"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    public void getUsersTest() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Jaime Che");

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(post("/api/users/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jaime Che"));
    }


    @Test
    public void getUserByTokenTest() throws Exception {
        String token = "dummy-token";
        UserResponse userResponse = new UserResponse();
        userResponse.setName("Jaime Che");

        when(userService.getUserByToken(anyString())).thenReturn(Optional.of(userResponse));

        mockMvc.perform(post("/api/users/get-by-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"" + token + "\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jaime Che"));
    }

}
