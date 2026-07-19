package com.example.dodast.Service;

import com.example.dodast.Repository.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.dodast.DTO.Advertisement.CreateAdvertisementRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Model.*;
import com.example.dodast.Model.Enums.AdvertisementStatus;
import com.example.dodast.Exception.ProvinceNotFoundException;
import com.example.dodast.Exception.CityNotFoundException;
import com.example.dodast.Exception.CityProvinceNotMatchException;
import com.example.dodast.Exception.CategoryNotFoundException;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import com.example.dodast.Exception.AdvertisementNotFoundException;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    public AdvertisementResponse createAdvertisement(CreateAdvertisementRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(CityNotFoundException::new);

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(ProvinceNotFoundException::new);

        checkCityProvinceMatch(city, province);

        User owner = AdAuthenticator.getCurrentUser();

        Advertisement advertisement = Advertisement.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .city(city)
                .province(province)
                .status(AdvertisementStatus.PENDING)
                .owner(owner)
                .build();

        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

        return new AdvertisementResponse(
                savedAdvertisement.getId(),
                savedAdvertisement.getTitle(),
                savedAdvertisement.getPrice(),
                savedAdvertisement.getCity().getName(),
                savedAdvertisement.getStatus()
        );
    }

    public AdvertisementResponse updateAdvertisement(
            Long id,
            UpdateAdvertisementRequest request) {

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(AdvertisementNotFoundException::new);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(CityNotFoundException::new);

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(ProvinceNotFoundException::new);

        checkCityProvinceMatch(city, province); 
        AdAuthenticator.checkOwner(advertisement,AdAuthenticator.getCurrentUser());

        advertisement.setTitle(request.getTitle());
        advertisement.setDescription(request.getDescription());
        advertisement.setPrice(request.getPrice());
        advertisement.setCategory(category);
        advertisement.setCity(city);
        advertisement.setProvince(province);

        advertisementRepository.save(advertisement);

        return new AdvertisementResponse(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getPrice(),
                advertisement.getCity().getName(),
                advertisement.getStatus()
        );
    }

    public void deleteAdvertisement(Long id) {

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(AdvertisementNotFoundException::new);

        AdAuthenticator.checkOwnerOrAdmin(advertisement, AdAuthenticator.getCurrentUser());

        advertisement.setStatus(AdvertisementStatus.DELETED);

        advertisementRepository.save(advertisement);
    }

    public void approveAdvertisement(Long id) {

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(AdvertisementNotFoundException::new);

        advertisement.setStatus(AdvertisementStatus.ACTIVE);

        advertisementRepository.save(advertisement);
    }

    public void rejectAdvertisement(Long id) {

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(AdvertisementNotFoundException::new);

        advertisement.setStatus(AdvertisementStatus.REJECTED);

        advertisementRepository.save(advertisement);
    }

    public void markAsSold(Long id) {

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(AdvertisementNotFoundException::new);

        AdAuthenticator.checkOwner(advertisement, AdAuthenticator.getCurrentUser());

        advertisement.setStatus(AdvertisementStatus.SOLD);

        advertisementRepository.save(advertisement);
    }

    private void checkCityProvinceMatch(City city, Province province){
        if(!city.getProvince().getId().equals(province.getId())) throw new CityProvinceNotMatchException();
    }

    public List<AdvertisementResponse> getPendingAdvertisements(){
        List<Advertisement> adList = advertisementRepository.findByStatus(AdvertisementStatus.PENDING);
        List<AdvertisementResponse> adResponseList = new ArrayList<>();

        for(Advertisement ad: adList){
                AdvertisementResponse adResponse = new AdvertisementResponse(
                        ad.getId(), 
                        ad.getTitle(), 
                        ad.getPrice(), 
                        ad.getCity().getName(), 
                        ad.getStatus()
                );

                adResponseList.add(adResponse);
        }

        return adResponseList;
    }

}

