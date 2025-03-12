package com.example.Rider_Co.services;

import com.example.Rider_Co.models.User;
import com.example.Rider_Co.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already taken";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()));
    }
}
