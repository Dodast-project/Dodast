package com.example.dodast.Service;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.DTO.Advertisement.AdSearchRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.InvalidPriceRangeException;

import lombok.RequiredArgsConstructor;

import com.example.dodast.Repository.AdvertisementRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    
    private final AdvertisementRepository advertisementRepository;

    public SearchService(AdvertisementRepository advertisementRepository){
        this.advertisementRepository = advertisementRepository;
    }

    public List<AdvertisementResponse> search(AdSearchRequest request){

        List<Advertisement> ads = new ArrayList<>();

        List<AdvertisementResponse> adResponses = new ArrayList<>();

        
        for(Advertisement ad: ads){

            
            AdvertisementResponse adResponse = new AdvertisementResponse(
                ad.getId(),
                ad.getTitle(),
                ad.getPrice(),
                ad.getCity().getName(),
                ad.getStatus()
            );

            adResponses.add(adResponse);
        }

        return adResponses;
    }

    private void validatePriceRange(AdSearchRequest request){
        if(request.getMaxPrice() < request.getMinPrice()) throw new InvalidPriceRangeException();
    }
}
