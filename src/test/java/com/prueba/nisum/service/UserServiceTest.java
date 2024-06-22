package com.prueba.nisum.service;

import com.prueba.nisum.dto.UserRequest;
import com.prueba.nisum.dto.UserRequest.Phone;
import com.prueba.nisum.dto.UserResponse;
import com.prueba.nisum.exception.EmailAlreadyExistsException;
import com.prueba.nisum.model.User;
import com.prueba.nisum.repository.UserRepository;
import com.prueba.nisum.util.JwtUtil;
import com.prueba.nisum.validation.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordValidator passwordValidator;

    @InjectMocks
    private UserService userService;

    @Test
    public void registerUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Jaime Che");
        userRequest.setEmail("jaime@gmail.com");
        userRequest.setPassword("password123");
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber("123456789");
        phone.setCitycode("1");
        phone.setContrycode("56");
        phones.add(phone);
        userRequest.setPhones(phones);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordValidator.validate(anyString())).thenReturn(true);
        when(jwtUtil.generateToken(anyString())).thenReturn("dummy-token");

        UserResponse userResponse = userService.registerUser(userRequest);

        assertNotNull(userResponse);
        assertEquals("Jaime Che", userResponse.getName());
        assertEquals("dummy-token", userResponse.getToken());
        assertTrue(userResponse.isActive());
    }

    @Test
    public void registerUserEmailAlreadyExistsTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Jaime Che");
        userRequest.setEmail("jaime@gmail.com");
        userRequest.setPassword("password123");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(userRequest));
    }

    @Test
    public void registerUserInvalidPasswordTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Jaime Che");
        userRequest.setEmail("jaime@gmail.com");
        userRequest.setPassword("password123");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordValidator.validate(anyString())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userRequest));
    }
}

