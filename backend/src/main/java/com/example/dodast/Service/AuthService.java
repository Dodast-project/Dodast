package com.example.dodast.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dodast.DTO.Auth.AuthResponse;
import com.example.dodast.DTO.Auth.LoginRequest;
import com.example.dodast.DTO.Auth.RegisterRequest;
import com.example.dodast.Exception.DuplicateEmailException;
import com.example.dodast.Exception.DuplicateUsernameException;
import com.example.dodast.Exception.InvalidPasswordException;
import com.example.dodast.Exception.UserNotFoundException;
import com.example.dodast.Model.User;
import com.example.dodast.Model.Enums.Role;
import com.example.dodast.Repository.UserRepository;

@Service
public class AuthService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())) throw new DuplicateUsernameException();
        if(userRepository.existsByEmail(request.getEmail())) throw new DuplicateEmailException();

        User user = new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setHashedPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return new AuthResponse("Register successful", savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }

    public AuthResponse login(LoginRequest request){

        User user = userRepository.findByUsernameOrEmail(request.getIdentifier(), request.getIdentifier()).orElseThrow(() -> new UserNotFoundException());

        if(!passwordEncoder.matches(request.getPassword(), user.getHashedPassword())) throw new InvalidPasswordException();

        return new AuthResponse("Login successful", user.getId(), user.getUsername(), user.getRole());
    }



}
