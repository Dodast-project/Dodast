package com.example.dodast.DTO.Advertisement;

import com.example.dodast.Model.SearchSortBy;

public class AdSearchRequest {

    private String keyword;

    private Long categoryId;

    private Long provinceId;

    private Long cityId;

    private Long minPrice;

    private Long maxPrice;

    private SearchSortBy sortBy;

    public AdSearchRequest(){}
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public SearchSortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SearchSortBy sortBy) {
        this.sortBy = sortBy;
    }
}