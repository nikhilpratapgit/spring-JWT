package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
//    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository,
                       JwtUtil jwtUtil,
                       BCryptPasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return user;
    }

    public String login(User user){
        User dbUser = repository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(
                user.getPassword(),
                dbUser.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }
        return jwtUtil.generateToken(dbUser.getEmail());
    }
}
