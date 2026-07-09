package com.example.dodast.DTO.Advertisement;

public class AdvertisementDetailResponse {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private String city;

    private String province;

    private String category;

    public AdvertisementDetailResponse(
            Long id,
            String title,
            String description,
            Long price,
            String city,
            String province,
            String category) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.city = city;
        this.province = province;
        this.category = category;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public Long getPrice() { return price; }

    public String getCity() { return city; }

    public String getProvince() { return province; }

    public String getCategory() { return category; }

}
