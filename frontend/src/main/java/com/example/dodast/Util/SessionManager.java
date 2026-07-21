package com.example.dodast.Util;

import com.example.dodast.DTO.Auth.AuthResponse;

public final class SessionManager {

    private static String token;
    private static Long userId;
    private static String username;
    private static String role;

    private SessionManager() {
    }

    public static void startSession(AuthResponse response) {
        token = response.getToken();
        userId = response.getId();
        username = response.getUsername();
        role = response.getRole();
    }

    public static void clearSession() {
        token = null;
        userId = null;
        username = null;
        role = null;
    }

    public static boolean isLoggedIn() {
        return token != null && !token.isBlank();
    }

    public static boolean isAdmin() {
        return role.equals("ADMIN");
    }

    public static String getToken() {
        return token;
    }

    public static Long getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }
}