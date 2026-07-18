package com.example.dodast.DTO.Advertisements;

import com.example.dodast.Model.Enums.SearchSortBy;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AdSearchRequest {

    @Size(max = 100, message = "Keyword cannot exceed 100 characters")
    private String keyword;
    private Long categoryId;
    private Long provinceId;
    private Long cityId;
    @Min(value = 0, message = "Minimum price cannot be negative")
    private Long minPrice;
    @Min(value = 0, message = "Maximum price cannot be negative")
    private Long maxPrice;
    private SearchSortBy sortBy;

    public AdSearchRequest(){}

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getKeyword() {
        return keyword;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public SearchSortBy getSortBy() {
        return sortBy;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public void setSortBy(SearchSortBy sortBy) {
        this.sortBy = sortBy;
    }

}
