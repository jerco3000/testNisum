package com.prueba.nisum.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetId() {
        UUID id = UUID.randomUUID();
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetName() {
        String name = "Jaime Che";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetEmail() {
        String email = "jaime@gmail.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetPassword() {
        String password = "password123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testSetPhones() {
        List<User.Phone> phones = new ArrayList<>();
        User.Phone phone = new User.Phone();
        phone.setNumber("123456789");
        phone.setCitycode("01");
        phone.setContrycode("57");
        phones.add(phone);
        user.setPhones(phones);
        assertEquals(phones, user.getPhones());
    }

    @Test
    public void testSetCreated() {
        LocalDateTime created = LocalDateTime.now();
        user.setCreated(created);
        assertEquals(created, user.getCreated());
    }

    @Test
    public void testSetModified() {
        LocalDateTime modified = LocalDateTime.now();
        user.setModified(modified);
        assertEquals(modified, user.getModified());
    }

    @Test
    public void testSetLastLogin() {
        LocalDateTime lastLogin = LocalDateTime.now();
        user.setLastLogin(lastLogin);
        assertEquals(lastLogin, user.getLastLogin());
    }

    @Test
    public void testSetToken() {
        String token = "token123";
        user.setToken(token);
        assertEquals(token, user.getToken());
    }

    @Test
    public void testSetActive() {
        boolean isActive = true;
        user.setActive(isActive);
        assertEquals(isActive, user.isActive());
    }
}
