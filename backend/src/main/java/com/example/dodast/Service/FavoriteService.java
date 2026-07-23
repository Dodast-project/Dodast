package com.example.dodast.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.AdvertisementImage;

import org.springframework.stereotype.Service;
import com.example.dodast.Repository.AdvertisementRepository;
import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.ImageResponse;
import com.example.dodast.Exception.FavoriteAlreadyExistsException;
import com.example.dodast.Repository.FavoriteRepository;
import com.example.dodast.Model.Favorite;
import com.example.dodast.Model.User;
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

        if(favoriteRepository.existsByUserIdAndAdvertisementId(user.getId(), advertisementId)) throw new FavoriteAlreadyExistsException();

        Favorite favorite = Favorite.builder()
            .user(user)
            .advertisement(advertisement)
            .build();

        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long advertisementId){
         User user = AdAuthenticator.getCurrentUser();

        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow(AdvertisementNotFoundException::new);

        Favorite favorite = favoriteRepository.findByUserIdAndAdvertisementId(user.getId(), advertisement.getId()).orElseThrow(FavoriteNotFoundException::new);


        favoriteRepository.delete(favorite);
    }

    public List<AdvertisementResponse> getFavorites(){

        User user = AdAuthenticator.getCurrentUser();

        List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());

        List<AdvertisementResponse> advertisements = new ArrayList<>();

        for(Favorite favorite : favorites){

            Advertisement advertisement = favorite.getAdvertisement();

            String imageUrl = null;

            if(advertisement.getImages() != null &&
            !advertisement.getImages().isEmpty()){

                imageUrl = advertisement.getImages().get(0).getImageUrl();
            }
            AdvertisementResponse response = new AdvertisementResponse(
                advertisement.getId(), 
                advertisement.getTitle(), 
                advertisement.getPrice(), 
                advertisement.getCity().getName(), 
                advertisement.getStatus(),
                imageUrl,
                true
            );

            advertisements.add(response);
        }

        return advertisements;
    }

    public AdvertisementDetailResponse getFavoriteDetail(Long favoriteId){

        User user = AdAuthenticator.getCurrentUser();

        Favorite favorite = favoriteRepository
                .findById(favoriteId)
                .orElseThrow(FavoriteNotFoundException::new);


        if(!favorite.getUser().getId().equals(user.getId())){
            throw new FavoriteNotFoundException();
        }


        Advertisement advertisement = favorite.getAdvertisement();

        List<ImageResponse> images = new ArrayList<>();

        for(AdvertisementImage image : advertisement.getImages()){

            ImageResponse imageResponse = new ImageResponse(image.getId(), image.getImageUrl());

            images.add(imageResponse);
        }
        AdvertisementDetailResponse advertisementDetailResponse = new AdvertisementDetailResponse(
            advertisement.getId(), 
            advertisement.getTitle(), 
            advertisement.getDescription(), 
            advertisement.getPrice(), 
            advertisement.getCity().getId(), 
            advertisement.getProvince().getId(), 
            advertisement.getCategory().getId(), 
            images, true);

        return  advertisementDetailResponse;
    }
}
