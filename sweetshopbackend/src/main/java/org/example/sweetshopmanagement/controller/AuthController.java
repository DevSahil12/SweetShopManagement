package org.example.sweetshopmanagement.controller;
import lombok.RequiredArgsConstructor;
import org.example.sweetshopmanagement.config.JwtUtil;
import org.example.sweetshopmanagement.dto.LoginRequest;
import org.example.sweetshopmanagement.dto.RegisterRequest;
import org.example.sweetshopmanagement.model.User;
import org.example.sweetshopmanagement.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return "Registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request);

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
