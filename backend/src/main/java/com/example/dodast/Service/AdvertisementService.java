package com.example.dodast.Service;

import com.example.dodast.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.dodast.DTO.Advertisement.CreateAdvertisementRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Model.*;
import com.example.dodast.Model.Enums.AdvertisementStatus;
import com.example.dodast.Exception.ProvinceNotFoundException;
import com.example.dodast.Exception.CityNotFoundException;
import com.example.dodast.Exception.CategoryNotFoundException;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import com.example.dodast.Exception.AdvertisementNotFoundException;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
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

        Advertisement advertisement = Advertisement.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .city(city)
                .province(province)
                .status(AdvertisementStatus.PENDING)
                .build();

        advertisementRepository.save(advertisement);

        return new AdvertisementResponse(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getPrice(),
                advertisement.getCity().getName(),
                advertisement.getStatus()
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

        advertisement.setStatus(AdvertisementStatus.SOLD);

        advertisementRepository.save(advertisement);
    }

}

