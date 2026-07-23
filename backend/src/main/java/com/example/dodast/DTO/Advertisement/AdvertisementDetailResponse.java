package com.example.dodast.DTO.Advertisement;

import java.util.List;

public class AdvertisementDetailResponse {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private Long cityId;

    private Long provinceId;

    private Long categoryId;

    private List<ImageResponse> images;

    private boolean isFavorite;


    public AdvertisementDetailResponse(
            Long id,
            String title,
            String description,
            Long price,
            Long cityId,
            Long provinceId,
            Long categoryId,
            List<ImageResponse> images,
            boolean isFavorite
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.categoryId = categoryId;
        this.images = images;
        this.isFavorite = isFavorite;
    }


    public List<ImageResponse> getImages() {
        return images;
    }
    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Long getCityId() {
        return cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public boolean isFavorite(){
        return isFavorite;
    }
}