package com.example.dodast.DTO.Advertisement;

import com.example.dodast.Model.Enums.AdvertisementStatus;

public class AdvertisementResponse {

    private Long id;

    private String title;

    private Long price;

    private String city;

    private AdvertisementStatus status;

    private String imageUrl;


    public AdvertisementResponse(
            Long id,
            String title,
            Long price,
            String city,
            AdvertisementStatus status,
            String imageUrl
    ){
        this.id = id;
        this.title = title;
        this.price = price;
        this.city = city;
        this.status = status;
        this.imageUrl = imageUrl;
    }


    public String getImageUrl(){
        return imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public AdvertisementStatus getStatus() {
        return status;
    }
}