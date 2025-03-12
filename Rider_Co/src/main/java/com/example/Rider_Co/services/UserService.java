package com.example.Rider_Co.services;

import com.example.Rider_Co.models.User;
import com.example.Rider_Co.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already taken";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userRepository.save(user);
        return "User registered successfully!";
    }

    public boolean authenticateUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        return existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword());
    }
}
