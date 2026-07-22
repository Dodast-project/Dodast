package com.example.dodast.Util;

public class NavigationSession {

    private static String previousPage = "home.fxml";

    public static void setPreviousPage(String page) {
        previousPage = page;
    }

    public static String getPreviousPage() {
        return previousPage;
    }
}