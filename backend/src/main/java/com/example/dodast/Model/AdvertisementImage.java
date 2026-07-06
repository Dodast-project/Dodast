package com.example.dodast.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advertisement_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertisementImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

}
