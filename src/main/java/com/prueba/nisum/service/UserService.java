package com.prueba.nisum.service;

import com.prueba.nisum.dto.UserRequest;
import com.prueba.nisum.dto.UserResponse;
import com.prueba.nisum.exception.EmailAlreadyExistsException;
import com.prueba.nisum.model.User;
import com.prueba.nisum.repository.UserRepository;
import com.prueba.nisum.util.JwtUtil;
import com.prueba.nisum.validation.PasswordValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordValidator passwordValidator;

    public UserResponse registerUser(@Valid UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya registrado");
        }

        if (!passwordValidator.validate(userRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }

        List<User.Phone> phones = userRequest.getPhones().stream()
                .map(phoneRequest -> {
                    User.Phone phone = new User.Phone();
                    phone.setNumber(phoneRequest.getNumber());
                    phone.setCitycode(phoneRequest.getCitycode());
                    phone.setContrycode(phoneRequest.getContrycode());
                    return phone;
                })
                .collect(Collectors.toList());

        User user = new User.Builder()
                .withName(userRequest.getName())
                .withEmail(userRequest.getEmail())
                .withPassword(userRequest.getPassword())
                .withPhones(phones)
                .withCreated(LocalDateTime.now())
                .withModified(LocalDateTime.now())
                .withLastLogin(LocalDateTime.now())
                .withToken(jwtUtil.generateToken(userRequest.getEmail()))
                .withIsActive(true)
                .build();

        userRepository.save(user);

        return createUserResponse(user);
    }

    private UserResponse createUserResponse(User user) {
        return new UserResponseFactory().create(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Optional<UserResponse> getUserByToken(String token) {
        String email = jwtUtil.getUsernameFromToken(token);
        return userRepository.findByEmail(email).map(this::createUserResponse);
    }
}

