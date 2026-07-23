package com.example.dodast.Util;

import com.example.dodast.DTO.Advertisement.AdSearchRequest;

public class SearchSession {

    private static AdSearchRequest lastSearch;

    public static void setLastSearch(AdSearchRequest request){
        lastSearch = request;
    }

    public static AdSearchRequest getLastSearch(){
        return lastSearch;
    }

    public static void clear(){
        lastSearch = null;
    }
}