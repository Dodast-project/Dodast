package com.example.dodast.DTO.Advertisement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateAdvertisementRequest {

    private String title;
    private String description;
    private Long price;
    private Long categoryId;
    private Long provinceId;
    private Long cityId;

    private List<File> images = new ArrayList<>();


    public CreateAdvertisementRequest() {}


    public CreateAdvertisementRequest(
            String title,
            String description,
            Long price,
            Long categoryId,
            Long provinceId,
            Long cityId,
            List<File> images) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.images = images;
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


    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }
}