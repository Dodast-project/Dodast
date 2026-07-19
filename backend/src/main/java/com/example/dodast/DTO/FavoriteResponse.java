package com.example.dodast.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteResponse {

    private Long favoriteId;

    private Long advertisementId;

    private String title;

    private Long price;

    private String city;

    private String province;

    private String category;

    private List<String> images;
}