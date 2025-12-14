package org.example.sweetshopmanagement.service;


import org.example.sweetshopmanagement.dto.LoginRequest;
import org.example.sweetshopmanagement.dto.RegisterRequest;
import org.example.sweetshopmanagement.model.User;

public interface UserService {
    void register(RegisterRequest request);
    User authenticate(LoginRequest request);

}

