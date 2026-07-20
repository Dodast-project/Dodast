package com.example.dodast.DTO.Auth;

public class AuthResponse {

    private String message;
    private Long id;
    private String username;
    private String role;
    private String token;

    public AuthResponse() {
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}