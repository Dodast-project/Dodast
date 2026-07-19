package com.example.dodast.DTO.Auth;

import com.example.dodast.Model.Enums.Role;

public class AuthResponse {
    private String message;
    private Long id;
    private String username;
    private Role role;
    private String token;

    public AuthResponse(String message, Long id, String username, Role role, String token){
        this.message = message;
        this.id = id;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
    
}
