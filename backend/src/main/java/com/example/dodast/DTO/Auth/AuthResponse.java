package com.example.dodast.DTO.Auth;

import com.example.dodast.Model.Enums.Role;

public class AuthResponse {
    private String message;
    private Long id;
    private String username;
    private Role role;
<<<<<<< HEAD
    private String token;

    public AuthResponse(String message, Long id, String username, Role role, String token){
=======

    public AuthResponse(String message, Long id, String username, Role role){
>>>>>>> helia/new/advertisement
        this.message = message;
        this.id = id;
        this.username = username;
        this.role = role;
<<<<<<< HEAD
        this.token = token;
=======
>>>>>>> helia/new/advertisement
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
<<<<<<< HEAD

    public String getToken() {
        return token;
    }
=======
>>>>>>> helia/new/advertisement
    
}
