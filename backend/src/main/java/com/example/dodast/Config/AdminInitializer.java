package com.example.dodast.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.dodast.Model.User;
import com.example.dodast.Model.Enums.Role;
import com.example.dodast.Repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void run(String... args){

        if(userRepository.existsByUsername("admin")) return;

        User admin = User.builder().fullName("Dodast Admin").username("admin").hashedPassword(passwordEncoder.encode("@admindodast@")).email("admin@dodast.com").role(Role.ADMIN).build();

        userRepository.save(admin);
    }
}
