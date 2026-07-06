package com.example.dodast.DTO.Advertisement;

import com.example.dodast.Model.Enums.AdvertisementStatus;

public class AdvertisementResponse {

    private Long id;

    private String title;

    private Long price;

    private String city;

    private AdvertisementStatus status;

    public AdvertisementResponse(Long id,
                                 String title,
                                 Long price,
                                 String city,
                                 AdvertisementStatus status) {

        this.id = id;
        this.title = title;
        this.price = price;
        this.city = city;
        this.status = status;
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
