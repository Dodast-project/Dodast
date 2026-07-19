package com.example.dodast.Service;

import org.springframework.data.domain.Sort;

import com.example.dodast.Model.Enums.SearchSortBy;

public class SearchRankingSystem {
    public Sort createSort(SearchSortBy sortBy){
        switch (sortBy) {
            case LOWESTPRICE:
                return Sort.by(Sort.Direction.ASC, "price");
        
            case HIGHESTPRICE:
                return Sort.by(Sort.Direction.DESC, "price");
            default:
                return Sort.unsorted();
        }
    }
}
