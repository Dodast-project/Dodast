package com.example.dodast.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dodast.DTO.FavoriteResponse;
import com.example.dodast.Service.FavoriteService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void setFavorite(@PathVariable Long advertisementId) {
        favoriteService.setFavorite(advertisementId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(@PathVariable Long advertisementId){
        favoriteService.removeFavorite(advertisementId);
    }
    
    @GetMapping
    public List<FavoriteResponse> getFavorites() {
        return favoriteService.getFavorites();
    }
    
}
