package com.example.dodast.DTO.Advertisement;

public class AdvertisementResponse {

    private Long id;

    private String title;

    private Long price;

    private String city;

    public AdvertisementResponse(Long id,
                                 String title,
                                 Long price,
                                 String city) {

        this.id = id;
        this.title = title;
        this.price = price;
        this.city = city;
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

}
