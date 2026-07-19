package com.example.dodast.Service;

import com.example.dodast.Model.Enums.Role;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.dodast.Exception.AdvertisementAccessDeniedException;
import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.User;

public class AdAuthenticator {
    public static void checkOwner(Advertisement advertisement, User currentUser){
        if(!advertisement.getOwner().getId().equals(currentUser.getId())) throw new AdvertisementAccessDeniedException();
    }

    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
                User user = (User) principal;
                return user;
        }
        throw new IllegalStateException("Authenticated user not found");
    }

    public static void checkOwnerOrAdmin(Advertisement advertisement, User currentUser){
        if(!advertisement.getOwner().getId().equals(currentUser.getId()) && !currentUser.getRole().equals(Role.ADMIN)) throw new AdvertisementAccessDeniedException();
    }
}
