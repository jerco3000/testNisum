package com.prueba.nisum.service;

import com.prueba.nisum.dto.UserResponse;
import com.prueba.nisum.model.User;

public class UserResponseFactory {

    public UserResponse create(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());
        return response;
    }
}

