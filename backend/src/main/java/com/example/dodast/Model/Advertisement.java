package com.example.dodast.Model;

import com.example.dodast.Model.Enums.AdvertisementStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advertisements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 3000)
    private String description;

    private Long price;

    @Enumerated(EnumType.STRING)
    private AdvertisementStatus status;

}
