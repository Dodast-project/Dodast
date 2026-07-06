package com.example.dodast.DTO.Advertisement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateAdvertisementRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 3000)
    private String description;

    @NotNull
    @Positive
    private Long price;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long provinceId;

    @NotNull
    private Long cityId;

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
