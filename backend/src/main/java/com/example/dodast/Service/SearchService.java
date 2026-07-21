package com.example.dodast.Service;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.User;
import com.example.dodast.DTO.Advertisement.AdSearchRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.InvalidPriceRangeException;
import com.example.dodast.Model.Enums.AdvertisementStatus;

import com.example.dodast.Repository.AdvertisementRepository;
import com.example.dodast.Repository.FavoriteRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    
    private final AdvertisementRepository advertisementRepository;
    private final SearchRankingSystem searchRankingSystem;
    private final FavoriteRepository favoriteRepository; 

    public SearchService(AdvertisementRepository advertisementRepository, SearchRankingSystem searchRankingSystem, FavoriteRepository favoriteRepository){
        this.advertisementRepository = advertisementRepository;
        this.searchRankingSystem = searchRankingSystem;
        this.favoriteRepository = favoriteRepository;
    }

    public List<AdvertisementResponse> search(AdSearchRequest request){

        validatePriceRange(request);

        String keyword = normalizeKeyword(request.getKeyword());

        Sort sort = searchRankingSystem.createSort(request.getSortBy());

        List<Advertisement> ads = advertisementRepository.searchAdvertisements(
            AdvertisementStatus.ACTIVE,
            keyword,
            request.getCategoryId(),
            request.getProvinceId(),
            request.getCityId(),
            request.getMinPrice(),
            request.getMaxPrice(),
            sort
        );

        List<AdvertisementResponse> adResponses = new ArrayList<>();

        
        for(Advertisement ad: ads){


            AdvertisementResponse adResponse = new AdvertisementResponse(
                ad.getId(),
                ad.getTitle(),
                ad.getPrice(),
                ad.getCity().getName(),
                ad.getStatus(),
                getFirstImage(ad),
                isFavorite(ad.getId())
            );

            adResponses.add(adResponse);
        }

        return adResponses;
    }

    private void validatePriceRange(AdSearchRequest request){
        Long minPrice = request.getMinPrice();
        Long maxPrice = request.getMaxPrice();

        if(minPrice != null 
            && maxPrice != null 
            && request.getMaxPrice() < request.getMinPrice()
        ) throw new InvalidPriceRangeException();
    }

    private String normalizeKeyword(String keyword){
        if(keyword == null || keyword.isBlank()) return null;
        return keyword.trim();
    }

    private String getFirstImage(Advertisement advertisement){

        if(advertisement.getImages() == null ||
        advertisement.getImages().isEmpty()) return null;

        return advertisement.getImages()
                .get(0)
                .getImageUrl();
    }

    private boolean isFavorite(Long advertisementId){

        User user = AdAuthenticator.getCurrentUser();

        return favoriteRepository.existsByUserIdAndAdvertisementId(user.getId(), advertisementId);
    }

    
}
