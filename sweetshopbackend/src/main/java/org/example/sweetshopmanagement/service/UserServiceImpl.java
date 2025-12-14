package org.example.sweetshopmanagement.service;


import lombok.RequiredArgsConstructor;
import org.example.sweetshopmanagement.dto.LoginRequest;
import org.example.sweetshopmanagement.dto.RegisterRequest;
import org.example.sweetshopmanagement.model.Role;
import org.example.sweetshopmanagement.model.User;
import org.example.sweetshopmanagement.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void register(RegisterRequest req) {

        String roleStr = req.getRole();

        // If frontend didn't send role or sent empty → default USER
        if (roleStr == null || roleStr.isBlank()) {
            roleStr = "USER";
        }

        Role role;
        try {
            role = Role.valueOf(roleStr.toUpperCase());  // convert to uppercase so "user" works
        } catch (Exception e) {
            role = Role.USER; // fallback if bad string
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(role);

        repo.save(u);
    }

    @Override
    public User authenticate(LoginRequest req) {
        User u = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.getPassword(), u.getPassword()))
            throw new RuntimeException("Invalid password");

        return u;
    }
}


