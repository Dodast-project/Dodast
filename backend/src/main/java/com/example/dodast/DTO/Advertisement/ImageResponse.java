package com.example.dodast.DTO.Advertisement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {

    private Long id;

    private String imageUrl;
}