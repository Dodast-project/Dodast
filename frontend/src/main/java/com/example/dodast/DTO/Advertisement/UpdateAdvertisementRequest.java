package com.example.dodast.DTO.Advertisement;

public class UpdateAdvertisementRequest {

    private String title;
    private String description;
    private Long price;
    private Long categoryId;
    private Long provinceId;
    private Long cityId;

    public UpdateAdvertisementRequest() {
    }

    public UpdateAdvertisementRequest(String title,
            String description,
            Long price,
            Long categoryId,
            Long provinceId,
            Long cityId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.provinceId = provinceId;
        this.cityId = cityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}