package org.example.sweetshopmanagement.service;
import org.example.sweetshopmanagement.model.User;
import org.example.sweetshopmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username){
        User u=userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole().name())
                .build();
    }
}

