package com.example.dodast.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dodast.DTO.FavoriteResponse;
import com.example.dodast.Exception.FavoriteAlreadyExistsException;
import com.example.dodast.Repository.FavoriteRepository;
import com.example.dodast.Model.Favorite;
import com.example.dodast.Model.User;
import com.example.dodast.Service.AdAuthenticator;
import com.example.dodast.Exception.AdvertisementNotFoundException;
import com.example.dodast.Exception.FavoriteNotFoundException;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final AdvertisementRepository advertisementRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, 
        AdvertisementRepository advertisementRepository
    ){
        this.advertisementRepository = advertisementRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public void setFavorite(Long advertisementId){
        User user = AdAuthenticator.getCurrentUser();

        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow(AdvertisementNotFoundException::new);

        if(favoriteRepository.existsByUserAndAdvertisementId(user.getId(), advertisementId)) throw new FavoriteAlreadyExistsException();

        Favorite favorite = Favorite.builder()
            .user(user)
            .advertisement(advertisement)
            .build();

        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long advertisementId){
         User user = AdAuthenticator.getCurrentUser();

        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow(AdvertisementNotFoundException::new);

        Favorite favorite = favoriteRepository.findByUserAndAdvertisementId(user.getId(), advertisementId).orElseThrow(FavoriteNotFoundException::new);


        favoriteRepository.delete(favorite);
    }

    public List<FavoriteResponse> getFavorites(){
        User user = AdAuthenticator.getCurrentUser();

        List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());

        List<FavoriteResponse> favoriteResponses = new ArrayList<>();

        for(Favorite favorite: favorites){
            FavoriteResponse favoriteResponse = FavoriteResponse.builder()
                                                                .favoriteId(favorite.getId())
                                                                .advertisementId(favorite.getAdvertisement().getId())
                                                                .title(favorite.getAdvertisement().getTitle())
                                                                .price(favorite.getAdvertisement().getPrice())
                                                                .city(favorite.getAdvertisement().getCity().getName())
                                                                .province(favorite.getAdvertisement().getProvince().getName())
                                                                .category(favorite.getAdvertisement().getCategory().getName())
                                                                .image(favorite.getAdvertisement().getImages())
                                                                .build();
            favoriteResponses.add(favoriteResponse);
        }
        return favoriteResponses;

    }
}