package com.example.dodast.Service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.dodast.Model.Enums.SearchSortBy;

@Component
public class SearchRankingSystem {

    public Sort createSort(SearchSortBy sortBy){

        if(sortBy == null)
            return Sort.by(Sort.Direction.DESC, "id");
        
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