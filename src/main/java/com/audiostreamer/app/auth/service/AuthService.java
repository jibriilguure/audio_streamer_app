package com.audiostreamer.app.auth.service;

import com.audiostreamer.app.auth.dto.AuthResponse;
import com.audiostreamer.app.auth.dto.LoginRequest;
import com.audiostreamer.app.auth.dto.RegisterRequest;
import com.audiostreamer.app.auth.model.Role;
import com.audiostreamer.app.auth.model.User;
import com.audiostreamer.app.auth.model.UserRepository;
import com.audiostreamer.app.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists by email or username
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // default to USER  for now
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate user (throws exception if invalid)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Get user from DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}
