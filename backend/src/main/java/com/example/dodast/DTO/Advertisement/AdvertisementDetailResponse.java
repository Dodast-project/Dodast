package com.example.dodast.DTO.Advertisement;

import java.util.List;

public class AdvertisementDetailResponse {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private String city;

    private String province;

    private String category;

    private List<ImageResponse> images;


    public AdvertisementDetailResponse(
            Long id,
            String title,
            String description,
            Long price,
            String city,
            String province,
            String category,
            List<ImageResponse> images
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.city = city;
        this.province = province;
        this.category = category;
        this.images = images;
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

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCategory() {
        return category;
    }
}