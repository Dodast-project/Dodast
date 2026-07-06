package com.example.dodast.DTO.Advertisement;

public class UpdateAdvertisementRequest {

    private String title;

    private String description;

    private Long price;

    private Long categoryId;

    private Long cityId;

    private Long provinceId;

    public UpdateAdvertisementRequest() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

}
